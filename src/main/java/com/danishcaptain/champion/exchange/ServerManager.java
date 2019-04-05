package com.danishcaptain.champion.exchange;

import com.danishcaptain.champion.application.ExecuteException;
import com.danishcaptain.champion.application.model.ApplicationModel;
import com.danishcaptain.champion.exchange.handler.ad.AdHandler;
import com.danishcaptain.champion.exchange.handler.ad.WinHandler;
import com.danishcaptain.champion.exchange.handler.bid.AdRequestHandler;
import com.danishcaptain.champion.exchange.handler.bid.CookieSyncHandler;
import com.danishcaptain.champion.exchange.handler.bid.HeaderBidAdRequestHandler;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class ServerManager {
    private final String KEY_SET = "DK_ServerManager";
    private int port;
    private final ApplicationModel model;
    private HttpsServer server;

    public ServerManager(ApplicationModel model) {
        this.model = model;
    }

    public void init() throws ExecuteException {
        model.initProperties(KEY_SET);
        port = Integer.parseInt(model.getProperty(KEY_SET, "exchange-port", "8080"));
        System.out.println("starting exchange on port: "+port);
        try {
            server = HttpsServer.create(new InetSocketAddress(port), 0);

            SSLContext sslContext = SSLContext.getInstance("TLS");

            // initialise the keystore
            char[] password = "kDKmHY8ecT9nY3WAwWhqk5ZnF4eTYX2b".toCharArray();
            KeyStore ks = KeyStore.getInstance("JKS");
            InputStream is = getClass().getClassLoader().getResourceAsStream("cert/pub.network.jks");
            ks.load(is, password);
            is.close();

            // setup the key manager factory
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, password);

            // setup the trust manager factory
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ks);

            // setup the HTTPS context and parameters
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            server.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
                public void configure(HttpsParameters params) {
                    try {
                        // initialise the SSL context
                        SSLContext c = SSLContext.getDefault();
                        SSLEngine engine = c.createSSLEngine();
                        params.setNeedClientAuth(false);
                        params.setCipherSuites(engine.getEnabledCipherSuites());
                        params.setProtocols(engine.getEnabledProtocols());

                        // get the default parameters
                        SSLParameters defaultSSLParameters = c.getDefaultSSLParameters();
                        params.setSSLParameters(defaultSSLParameters);

                    } catch (Exception ex) {
                        System.out.println("Failed to create HTTPS port");
                    }
                }
            });


            server.createContext("/header-bid", new ExchangeContext(model, HeaderBidAdRequestHandler.class));
            server.createContext("/bid", new ExchangeContext(model, AdRequestHandler.class));
            server.createContext("/ad", new ExchangeContext(model, AdHandler.class));
            server.createContext("/win", new ExchangeContext(model, WinHandler.class));
            server.createContext("/cookie-sync", new ExchangeContext(model, CookieSyncHandler.class));
            server.setExecutor(null); // creates a default executor
        } catch (IOException e) {
            throw new ExecuteException(e);
        } catch (CertificateException e) {
            throw new ExecuteException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new ExecuteException(e);
        } catch (UnrecoverableKeyException e) {
            throw new ExecuteException(e);
        } catch (KeyStoreException e) {
            throw new ExecuteException(e);
        } catch (KeyManagementException e) {
            throw new ExecuteException(e);
        }
    }

    public final void start() throws ExecuteException {
        server.start();
    }

    public void stop() throws ExecuteException {
    }
}

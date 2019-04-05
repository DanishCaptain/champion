package com.danishcaptain.champion.exchange.handler;

import com.danishcaptain.champion.application.ExecuteException;
import com.danishcaptain.champion.application.model.ApplicationModel;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public abstract class JsonHandler extends ExchangeHandler {

    public JsonHandler(ApplicationModel model, HttpExchange exchange) {
        super(model, exchange);
    }


    /*
https://ads.mopub.com/m/ad?v=6&id=a935eac11acd416f92640411234fbba6&nv=5.4.1&dn=Google%2CAndroid%20SDK%20built%20for%20x86%2Csdk_google_phone_x86&bundle=org.prebid.mobile.api1demo&q=hb_cache_id%3Ab831c322-ad76-4568-bfe7-a1668378ea12%2Chb_bidder_appnexus%3Aappnexus%2Chb_size_appnexus%3A300x250%2Chb_bidder%3Aappnexus%2Chb_cache_id_appnexus%3Ab831c322-ad76-4568-bfe7-a1668378ea12%2Chb_size%3A300x250%2Chb_env_appnexus%3Amobile-app%2Chb_pb_appnexus%3A0.50%2Chb_pb%3A0.50%2Chb_env%3Amobile-app%2C&z=-0700&o=p&w=1080&h=1920&sc=2.625&mcc=310&mnc=260&cn=Android&ct=6&av=1.0&udid=mp_tmpl_advertising_id&dnt=mp_tmpl_do_not_track&gdpr_applies=0&force_gdpr_applies=0&current_consent_status=unknown&mr=1&android_perms_ext_storage=0&vv=3
     */

    @Override
    protected void process(HttpExchange exchange) throws ExecuteException {
        try {
            if ("post".equalsIgnoreCase(exchange.getRequestMethod())) {

            } else {

            }
            Map<String, String> parms = ParameterHandler.queryToMap(exchange.getRequestURI().getQuery());

            Headers reqHeaders = exchange.getRequestHeaders();

            // some small sanity check
            List<String> cookies = reqHeaders.get("Cookie");

            BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
            String line;
            StringBuilder rawRequest = new StringBuilder();
            while((line = br.readLine()) != null) {
                rawRequest.append(line+"\n");
            }
            String result = processJson(cookies, rawRequest.toString());

            exchange.sendResponseHeaders(200, result.length());
            OutputStream os = exchange.getResponseBody();
            os.write(result.getBytes());
            os.close();
        } catch (IOException e) {
            throw new ExecuteException(e);
        }
    }

    protected abstract String processJson(List<String> cookies, String json);

}

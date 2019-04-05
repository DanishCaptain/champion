package com.danishcaptain.champion.exchange;

import com.danishcaptain.champion.application.model.ApplicationModel;
import com.danishcaptain.champion.domain.bid.HeaderBidAdRequest;
import com.danishcaptain.champion.domain.bid.HeaderBidAdResponse;
import com.danishcaptain.champion.exchange.handler.ExchangeHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ExchangeContext<T extends ExchangeHandler> implements HttpHandler {

    private ApplicationModel model;
    private final Class<T> handlerClass;

    public ExchangeContext(ApplicationModel model, Class<T> handlerClass) {
        this.model = model;
        this.handlerClass = handlerClass;
    }

    @Override
    public final void handle(HttpExchange httpExchange) throws IOException {
        try {
            Constructor c = handlerClass.getConstructor(new Class[] {ApplicationModel.class, HttpExchange.class});
            ExchangeHandler h = (ExchangeHandler) c.newInstance(new Object[]{model, httpExchange});
            h.start();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}

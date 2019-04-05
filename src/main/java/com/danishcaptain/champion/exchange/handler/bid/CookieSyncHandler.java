package com.danishcaptain.champion.exchange.handler.bid;

import com.danishcaptain.champion.application.model.ApplicationModel;
import com.danishcaptain.champion.exchange.handler.ParameterHandler;
import com.sun.net.httpserver.HttpExchange;

import java.util.Map;

public class CookieSyncHandler extends ParameterHandler {

    public CookieSyncHandler(ApplicationModel model, HttpExchange exchange) {
        super(model, exchange);
    }

    @Override
    protected String process(Map<String, String> parms) {
        return "";
    }

}

package com.danishcaptain.champion.exchange.handler.ad;

import com.danishcaptain.champion.application.model.ApplicationModel;
import com.danishcaptain.champion.exchange.handler.ParameterHandler;
import com.sun.net.httpserver.HttpExchange;

import java.util.Map;

public class WinHandler extends ParameterHandler {

    public WinHandler(ApplicationModel model, HttpExchange exchange) {
        super(model, exchange);
    }

    @Override
    protected String process(Map<String, String> parms) {
        return "";
    }

}

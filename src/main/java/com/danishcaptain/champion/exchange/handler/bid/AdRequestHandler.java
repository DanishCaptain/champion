package com.danishcaptain.champion.exchange.handler.bid;

import com.danishcaptain.champion.application.model.ApplicationModel;
import com.danishcaptain.champion.domain.bid.AdRequest;
import com.danishcaptain.champion.domain.bid.AdResponse;
import com.danishcaptain.champion.exchange.handler.JsonHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;

import java.util.List;

public class AdRequestHandler extends JsonHandler {
    public AdRequestHandler(ApplicationModel model, HttpExchange exchange) {
        super(model, exchange);
    }

    @Override
    protected String processJson(List<String> cookies, String json) {
        System.out.println(json);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        AdRequest request = gson.fromJson(json, AdRequest.class);

        System.out.println(request);

        AdResponse response = new AdResponse();
        response.setId(request.getId());

        if (request.getSite() != null) {

        } else if (request.getApp() != null) {

        } else {

        }
        System.out.println(request.getSite());
        System.out.println(request.getApp());
        System.out.println(request.getPage());
        System.out.println(request.getFshash());
        System.out.println(request.getFsloc());
        System.out.println(request.getFsuid());
        System.out.println(request.getFssid());

        String jResponse = gson.toJson(response);

        return jResponse;
    }
}

package com.danishcaptain.champion.exchange.handler.bid;

import com.danishcaptain.champion.application.model.ApplicationModel;
import com.danishcaptain.champion.domain.bid.HeaderBidAdRequest;
import com.danishcaptain.champion.domain.bid.HeaderBidAdResponse;
import com.danishcaptain.champion.exchange.handler.JsonHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;

import java.util.List;

public class HeaderBidAdRequestHandler extends JsonHandler {
    public HeaderBidAdRequestHandler(ApplicationModel model, HttpExchange exchange) {
        super(model, exchange);
    }

    @Override
    protected String processJson(List<String> cookies, String json) {
        System.out.println(json);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        HeaderBidAdRequest request = gson.fromJson(json, HeaderBidAdRequest.class);

        System.out.println(request);

        HeaderBidAdResponse response = new HeaderBidAdResponse();
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

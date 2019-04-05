package com.danishcaptain.champion.exchange.handler;

import com.danishcaptain.champion.application.ExecuteException;
import com.danishcaptain.champion.application.model.ApplicationModel;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public abstract class ParameterHandler extends ExchangeHandler {

    public ParameterHandler(ApplicationModel model, HttpExchange exchange) {
        super(model, exchange);
    }

    @Override
    protected void process(HttpExchange exchange) throws ExecuteException {
        try {
            Map<String, String> parms = queryToMap(exchange.getRequestURI().getQuery());
            String result = process(parms);
            exchange.sendResponseHeaders(200, result.length());
            OutputStream os = exchange.getResponseBody();
            os.write(result.getBytes());
            os.close();
        } catch (IOException e) {
            throw new ExecuteException(e);
        }
    }

    protected abstract String process(Map<String, String> parms);

    public static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<String, String>();
        if (query != null) {
            for (String param : query.split("&")) {
                String pair[] = param.split("=");
                if (pair.length > 1) {
                    result.put(pair[0], pair[1]);
                } else {
                    result.put(pair[0], "");
                }
            }
        }
        return result;
    }
}

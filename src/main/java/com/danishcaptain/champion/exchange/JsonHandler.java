package com.danishcaptain.champion.exchange;

import com.danishcaptain.champion.application.model.ApplicationModel;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public abstract class JsonHandler implements HttpHandler {
    private final ApplicationModel model;

    public JsonHandler(ApplicationModel model) {
        this.model = model;
    }

    @Override
    public final void handle(HttpExchange httpExchange) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
        String line;
        StringBuilder rawRequest = new StringBuilder();
        while((line = br.readLine()) != null) {
            rawRequest.append(line+"\n");
        }

        String result = processJson(rawRequest.toString());

        httpExchange.sendResponseHeaders(200, result.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(result.getBytes());
        os.close();
    }

    protected abstract String processJson(String json);

    protected ApplicationModel getModel() {
        return model;
    }

}

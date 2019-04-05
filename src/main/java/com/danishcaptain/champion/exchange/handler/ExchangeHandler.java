package com.danishcaptain.champion.exchange.handler;

import com.danishcaptain.champion.application.ExecuteException;
import com.danishcaptain.champion.application.model.ApplicationModel;
import com.sun.net.httpserver.HttpExchange;

public abstract class ExchangeHandler implements Runnable {
    private final HttpExchange exchange;
    private final ApplicationModel model;
    private Thread t;

    public ExchangeHandler(ApplicationModel model, HttpExchange exchange) {
        this.model = model;
        this.exchange = exchange;
    }

    protected ApplicationModel getModel() {
        return model;
    }

    protected HttpExchange getExchange() {
        return exchange;
    }

    public final void start() {
        if (t == null) {
            t = new Thread(this);
            t.setName(getClass().getSimpleName());
        }
        t.start();
    }

    @Override
    public final void run() {
        HttpExchange exchange = getExchange();
        try {
            process(exchange);
        } catch (ExecuteException e) {
            e.printStackTrace();
        }
    }

    protected abstract void process(HttpExchange exchange) throws ExecuteException;

}

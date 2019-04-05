package com.danishcaptain.champion.application;

import com.danishcaptain.champion.exchange.ServerManager;

public class Champion extends Application {
    private final ServerManager manager;

    public Champion() {
        manager = new ServerManager(getModel());
    }

    @Override
    protected void initApplication() throws ExecuteException {
        manager.init();
    }

    @Override
    protected void startApplication() throws ExecuteException {
        manager.start();
    }

    @Override
    protected void stopApplication() throws ExecuteException {
        manager.stop();
    }

    public static void main(String[] args) {
        Champion app = new Champion();
        try {
            app.init();
        } catch (ExecuteException e) {
            e.printStackTrace();
        }
        try {
            app.start();
        } catch (ExecuteException e) {
            e.printStackTrace();
        }
    }

}

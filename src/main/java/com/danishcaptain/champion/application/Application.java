package com.danishcaptain.champion.application;

import com.danishcaptain.champion.application.model.ApplicationModel;

public abstract class Application {
    private final ApplicationModel model;

    public Application() {
        model = new ApplicationModel();
    }

    protected ApplicationModel getModel() {
        return model;
    }

    public final void init() throws ExecuteException {
        model.init();
        initApplication();
    }

    protected abstract void initApplication() throws ExecuteException;

    public final void start() throws ExecuteException {
        model.start();
        startApplication();
    }

    protected abstract void startApplication() throws ExecuteException;

    public final void stop() throws ExecuteException {
        stopApplication();
        model.stop();
    }

    protected abstract void stopApplication() throws ExecuteException;
}

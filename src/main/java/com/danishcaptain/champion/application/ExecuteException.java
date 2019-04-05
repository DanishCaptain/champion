package com.danishcaptain.champion.application;

import java.io.PrintStream;
import java.io.PrintWriter;

public class ExecuteException extends Exception {
    private Throwable t;

    public ExecuteException() {
    }

    public ExecuteException(Throwable t) {
        this.t = t;
    }

    @Override
    public void printStackTrace() {
        if (t != null) {
            t.printStackTrace();
        } else {
            super.printStackTrace();
        }
    }

    @Override
    public void printStackTrace(PrintStream p) {
        if (t != null) {
            t.printStackTrace(p);
        } else {
            super.printStackTrace(p);
        }
    }

    @Override
    public void printStackTrace(PrintWriter p) {
        if (t != null) {
            t.printStackTrace(p);
        } else {
            super.printStackTrace(p);
        }
    }
}

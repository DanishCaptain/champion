package com.danishcaptain.champion.domain.bid;

import java.util.ArrayList;
import java.util.UUID;

public class AdRequest {
    private ArrayList<AdUnit> adUnitsToBidUpon = new ArrayList<>();
    private UUID id;
    private String site;
    private String app;
    private String page;
    private String _fshash;
    private String _fsloc;
    private String _fsuid;
    private String _fssid;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getFshash() {
        return _fshash;
    }

    public void setFshash(String fshash) {
        this._fshash = fshash;
    }

    public String getFsloc() {
        return _fsloc;
    }

    public void setFsloc(String fsloc) {
        _fsloc = fsloc;
    }

    public String getFsuid() {
        return _fsuid;
    }

    public void setFsuid(String fsuid) {
        _fsuid = fsuid;
    }

    public String getFssid() {
        return _fssid;
    }

    public void setFssid(String fssid) {
        _fssid = fssid;
    }
}

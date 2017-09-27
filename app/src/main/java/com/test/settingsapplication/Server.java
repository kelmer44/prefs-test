package com.test.settingsapplication;

/**
 * Created by gabriel on 27/09/2017.
 */


public class Server {

    private long id;
    private String continentCode;
    private String name;
    private String ip;
    private String host;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Server withId(long id) {
        this.id = id;
        return this;
    }

    public String getContinentCode() {
        return continentCode;
    }

    public void setContinentCode(String continentCode) {
        this.continentCode = continentCode;
    }

    public Server withContinentCode(String continentCode) {
        this.continentCode = continentCode;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Server withName(String name) {
        this.name = name;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Server withIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Server withHost(String host) {
        this.host = host;
        return this;
    }



}
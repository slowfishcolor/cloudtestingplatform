package com.mist.cloudtestingplatform.protocol.model;

/**
 * Created by Prophet on 2017/11/22.
 */
public class Option {

    // qos in mqtt, 1 at least once, 2 exactly once
    private int qos = 0;

    private String username;

    private String password;
    // broker url
    private String url;
    // 1 subscribe
    private int subscribe = 0;
    // 1 publish
    private int publish = 1;

    private String will;
    // 1 persistant at broker
    private int persistance = 0;

    public int getQos() {
        return qos;
    }

    public void setQos(int qos) {
        this.qos = qos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public int getPublish() {
        return publish;
    }

    public void setPublish(int publish) {
        this.publish = publish;
    }

    public String getWill() {
        return will;
    }

    public void setWill(String will) {
        this.will = will;
    }

    public int getPersistance() {
        return persistance;
    }

    public void setPersistance(int persistance) {
        this.persistance = persistance;
    }
}

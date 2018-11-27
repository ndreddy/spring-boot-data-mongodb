package com.ngrd.cloud.adminapi.customer;

import org.springframework.data.annotation.Id;

public class Customer {

    @Id
    private String id;
    private String name;
    private String apiKeyId;
    private String apiKey;
    private String appUrl;
    private String usagePlanId;
    private String usagePlanKeyId;


    public String getUsagePlanId() {
        return usagePlanId;
    }

    public void setUsagePlanId(String usagePlanId) {
        this.usagePlanId = usagePlanId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Customer withApiKey(String apiKey){
        this.setApiKey(apiKey);
        return this;
    }

    public Customer withName(String name){
        this.setName(name);
        return this;
    }
    public Customer withAppUrl(String appUrl){
        this.setAppUrl(appUrl);
        return this;
    }

    public Customer withUsagePlanId(String usagePlanId){
        this.setUsagePlanId(usagePlanId);
        return this;
    }

    public String getUsagePlanKeyId() {
        return usagePlanKeyId;
    }

    public void setUsagePlanKeyId(String usagePlanKeyId) {
        this.usagePlanKeyId = usagePlanKeyId;
    }


    public String getApiKeyId() {
        return apiKeyId;
    }

    public void setApiKeyId(String apiKeyId) {
        this.apiKeyId = apiKeyId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Customer{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", apiKeyId='").append(apiKeyId).append('\'');
        sb.append(", apiKey='").append(apiKey).append('\'');
        sb.append(", appUrl='").append(appUrl).append('\'');
        sb.append(", usagePlanId='").append(usagePlanId).append('\'');
        sb.append(", usagePlanKeyId='").append(usagePlanKeyId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

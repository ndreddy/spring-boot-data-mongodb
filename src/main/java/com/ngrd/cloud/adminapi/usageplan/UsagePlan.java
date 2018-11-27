package com.ngrd.cloud.adminapi.usageplan;

import java.util.ArrayList;

public class UsagePlan {
    private String name;
    private String description;
    ArrayList<Object> apiStages = new ArrayList<Object>();
    Quota QuotaObject;
    Throttle ThrottleObject;


    // Getter Methods

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Quota getQuota() {
        return QuotaObject;
    }

    public Throttle getThrottle() {
        return ThrottleObject;
    }

    // Setter Methods

    public void setName( String name ) {
        this.name = name;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public void setQuota( Quota quotaObject ) {
        this.QuotaObject = quotaObject;
    }

    public void setThrottle( Throttle throttleObject ) {
        this.ThrottleObject = throttleObject;
    }
}
 class Throttle {
    private float rateLimit;
    private float burstLimit;


    // Getter Methods

    public float getRateLimit() {
        return rateLimit;
    }

    public float getBurstLimit() {
        return burstLimit;
    }

    // Setter Methods

    public void setRateLimit( float rateLimit ) {
        this.rateLimit = rateLimit;
    }

    public void setBurstLimit( float burstLimit ) {
        this.burstLimit = burstLimit;
    }
}
 class Quota {
    private String period;
    private float offset;
    private float limit;


    // Getter Methods

    public String getPeriod() {
        return period;
    }

    public float getOffset() {
        return offset;
    }

    public float getLimit() {
        return limit;
    }

    // Setter Methods

    public void setPeriod( String period ) {
        this.period = period;
    }

    public void setOffset( float offset ) {
        this.offset = offset;
    }

    public void setLimit( float limit ) {
        this.limit = limit;
    }
}
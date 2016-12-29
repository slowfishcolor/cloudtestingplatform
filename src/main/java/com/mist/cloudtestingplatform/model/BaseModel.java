package com.mist.cloudtestingplatform.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by Prophet on 2016/12/26.
 */
public abstract class BaseModel implements Serializable{
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

package com.mist.cloudtestingplatform.service;

import com.mist.cloudtestingplatform.model.Model;
import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * Created by Prophet on 2017/11/16.
 */
public interface ModelService {

    @NotNull
    public List<Model> listModel();

    public Model getModelById(Integer id);

}

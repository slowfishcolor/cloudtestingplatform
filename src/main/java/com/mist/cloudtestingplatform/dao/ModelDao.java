package com.mist.cloudtestingplatform.dao;



import com.mist.cloudtestingplatform.model.Model;

import java.util.Collection;
import java.util.List;

/**
 * Created by Prophet on 2017/11/5.
 */
public interface ModelDao {

    public List<Model> listAllModel();

    public Model getModel(Integer modelId);

    public List<Model> listModelIn(Collection<Integer> idList);
}

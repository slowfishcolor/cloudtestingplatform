package com.mist.cloudtestingplatform.service.impl;

import com.mist.cloudtestingplatform.dao.ModelDao;
import com.mist.cloudtestingplatform.model.Model;
import com.mist.cloudtestingplatform.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prophet on 2017/11/16.
 */
@Transactional
@Service
public class ModelServiceImpl implements ModelService {

    private ModelDao modelDao;

    @Autowired
    public void setModelDao(ModelDao modelDao) {
        this.modelDao = modelDao;
    }

    @Override
    public List<Model> listModel() {
        List<Model> result = modelDao.listAllModel();
        if (result == null) {
            return new ArrayList<>();
        }
        return result;
    }

    @Override
    public Model getModelById(Integer id) {
        return modelDao.getModel(id);
    }
}

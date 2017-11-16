package com.mist.cloudtestingplatform.web;

import com.mist.cloudtestingplatform.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * Created by Prophet on 2017/11/16.
 */
@SessionAttributes("user")
@Controller
public class ModelController {


    private ModelService modelService;

    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    /***************************** pages *****************************/

    @RequestMapping(value = "/model-list", method = RequestMethod.GET)
    public String modelListPage(Model model) {
        List<com.mist.cloudtestingplatform.model.Model> models = modelService.listModel();
        model.addAttribute("models", models);
        return "model-list";
    }

}

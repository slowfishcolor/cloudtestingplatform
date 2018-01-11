package com.mist.cloudtestingplatform.web;

import com.mist.cloudtestingplatform.annotation.Auth;
import com.mist.cloudtestingplatform.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Prophet on 2017/11/16.
 */
@Auth
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

    @RequestMapping(value = "/model-info/{modelId}", method = RequestMethod.GET)
    public String modelInfoPage(@PathVariable Integer modelId, Model model) {
        com.mist.cloudtestingplatform.model.Model deviceModel
                = modelService.getModelById(modelId);
        model.addAttribute("model", deviceModel);
        return "model-info";
    }

    /***************************** apis *****************************/
    @ResponseBody
    @RequestMapping(value = "/api/getAllModel", method = RequestMethod.GET)
    public List<com.mist.cloudtestingplatform.model.Model> generateDeviceId() {
        return modelService.listModel();
    }

}

package ua.ros.taxiapp.web.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.ros.taxiapp.domain.Comment;
import ua.ros.taxiapp.domain.Favourite;
import ua.ros.taxiapp.domain.Model;
import ua.ros.taxiapp.services.ModelService;

import java.util.List;

@Controller
@RequestMapping("/rest/models")
public class ModelController {

    @Autowired
    private ModelService modelService;

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Model> getAllModels() {
        return modelService.getAllModels();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Model findModelById(@PathVariable("id") Integer id) {
        return modelService.findById(id);
    }

}

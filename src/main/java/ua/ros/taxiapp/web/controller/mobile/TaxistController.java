package ua.ros.taxiapp.web.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.ros.taxiapp.domain.*;
import ua.ros.taxiapp.services.TaxistService;

import java.util.List;

@Controller
@RequestMapping("/rest/taxists")
public class TaxistController {

    @Autowired
    private TaxistService taxistService;

    public void setTaxistService(TaxistService taxistService) {
        this.taxistService = taxistService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Taxist> getAllTaxists() {
        return taxistService.getAllTaxists();
    }

    @RequestMapping(value = "/free", method = RequestMethod.GET)
    @ResponseBody
    public List<Taxist> getAllFreeTaxists() {
        return taxistService.getAllFreeTaxists();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Taxist findTaxistById(@PathVariable("id") Integer id) {
        return taxistService.findById(id);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public StatusMessage saveTaxist(@RequestParam("user") User user, @RequestParam("car") Car car) {
        user.setTaxist(true);
        Taxist taxist = new Taxist();
        taxist.setUser(user);
        if(taxistService.createNewTaxist(taxist)) {
            return new StatusMessage(StatusMessage.OK);
        } else {
            return new StatusMessage(StatusMessage.FAIL);
        }
    }
}

package ua.ros.taxiapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.domain.Taxist;
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
}

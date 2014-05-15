package ua.ros.taxiapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.ros.taxiapp.domain.Brand;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.services.BrandService;
import ua.ros.taxiapp.services.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/rest/brands")
public class BrandController {
    // private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    private BrandService brandService;

    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Brand findBrandById(@PathVariable("id") Integer id) {
        return brandService.findById(id);
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    @ResponseBody
    public Brand findBrandWithName(@RequestParam("name") String name) {
        return brandService.findByName(name);
    }
}

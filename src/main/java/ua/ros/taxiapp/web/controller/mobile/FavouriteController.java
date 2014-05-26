package ua.ros.taxiapp.web.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.ros.taxiapp.domain.Comment;
import ua.ros.taxiapp.domain.Favourite;
import ua.ros.taxiapp.services.FavouriteService;

import java.util.List;

@Controller
@RequestMapping("/rest/favourites")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;

    public void setFavouriteService(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Favourite> getAllFavourites() {
        return favouriteService.getAllFavourites();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Favourite findFavouriteById(@PathVariable("id") Integer id) {
        return favouriteService.findById(id);
    }

}

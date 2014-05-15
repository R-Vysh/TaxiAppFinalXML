package ua.ros.taxiapp.services;

import ua.ros.taxiapp.domain.Favourite;
import ua.ros.taxiapp.domain.Model;

import java.util.List;

public interface ModelService {
    public boolean createModel(Model model);

    public Model findById(Integer id);

    public List<Model> getAllModels();

    public boolean deleteModel(Model model);

    public boolean updateModel(Model model);

}

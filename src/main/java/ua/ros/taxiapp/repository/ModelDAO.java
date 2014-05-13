package ua.ros.taxiapp.repository;

import ua.ros.taxiapp.domain.Brand;
import ua.ros.taxiapp.domain.Model;

import java.util.List;

public interface ModelDAO extends GenericDAO<Model, Integer> {

    public Model findByName(String name);

    public List<Model> findByBrand(Brand brand);
}

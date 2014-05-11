package ua.ros.taxiapp.repository;

import ua.ros.taxiapp.domain.Brand;

public interface BrandDAO extends GenericDAO<Brand, Integer> {

    public Brand findByName(String name);
}

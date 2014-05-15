package ua.ros.taxiapp.repository;

import ua.ros.taxiapp.domain.Taxist;

import java.util.List;

public interface TaxistDAO extends GenericDAO<Taxist, Integer>{
    public Taxist findByMobile(String mobile);

    List<Taxist> findAllFree();
}

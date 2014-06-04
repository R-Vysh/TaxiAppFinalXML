package ua.ros.taxiapp.repository;

import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.domain.User;

import java.util.List;

public interface TaxistDAO extends GenericDAO<Taxist, Integer>{
    public Taxist findByMobile(String mobile);

    public List<Taxist> findAllFree();

    public Taxist findByUser(User user);
}

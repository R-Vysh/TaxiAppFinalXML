package ua.ros.taxiapp.services;

import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.domain.User;

import java.io.Serializable;
import java.util.List;

public interface TaxistService {

    public List<Taxist> getAllFreeTaxists();

    public List<Taxist> getAllTaxists();

    public Taxist findById(Integer id);

    public boolean createNewTaxist(Taxist taxist);

    public boolean deleteTaxist(Taxist taxist);

    public Taxist findByUser(User user);

    boolean updateTaxist(Taxist taxist);
}

package ua.ros.taxiapp.services;

import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.domain.User;

import java.util.List;

public interface TaxistService {

    public List<Taxist> getAllFreeTaxists();

    public List<Taxist> getAllTaxists();

    public Taxist findById(Integer id);

    public boolean createTaxist(Taxist taxist);

    public boolean deleteTaxist(Taxist taxist);

    public Taxist findByUser(User user);

    public boolean updateTaxist(Taxist taxist);

    public boolean setOffline(Taxist taxist);

    public boolean setOnline(Taxist taxist);

    public boolean saveTaxist(Taxist taxist);
}

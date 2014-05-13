package ua.ros.taxiapp.services;

import ua.ros.taxiapp.domain.Taxist;

import java.io.Serializable;
import java.util.List;

public interface TaxistService {

    public List<Taxist> requestAllFreeTaxists();

    public Taxist requestTaxist(Serializable id);

    public boolean createNewTaxist(Taxist taxist);

    public boolean deleteTaxist(Serializable id);
}

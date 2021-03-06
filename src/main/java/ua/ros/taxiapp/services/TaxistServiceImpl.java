package ua.ros.taxiapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ua.ros.taxiapp.domain.*;
import ua.ros.taxiapp.repository.TaxistDAO;

import java.util.HashSet;
import java.util.List;

@Service
public class TaxistServiceImpl implements TaxistService {

    @Autowired
    TaxistDAO taxistDAO;

    @Autowired
    ModelService modelService;

    public TaxistDAO getTaxistDAO() {
        return taxistDAO;
    }

    public void setTaxistDAO(TaxistDAO taxistDAO) {
        this.taxistDAO = taxistDAO;
    }

    @Override
    public List<Taxist> getAllFreeTaxists() {
        return taxistDAO.findAllFree();
    }

    @Override
    public List<Taxist> getAllTaxists() {
        return taxistDAO.findAll(Taxist.class);
    }

    @Override
    public Taxist findById(Integer id) {
        return taxistDAO.findByID(Taxist.class, id);
    }

    @Override
    public boolean createTaxist(Taxist taxist) {
        Model existingModel = modelService.findByName(taxist.getCar().getModel().getModelsName());
        if (existingModel != null &&
                existingModel.getBrand().getBrandsName()
                        .equals(taxist.getCar().getModel().getBrand().getBrandsName())) {
            taxist.getCar().setModel(existingModel);
        }
        taxist.getUser().setTaxist(true);
        taxist.setCoordinates(new Coordinates());
        taxist.setCurrentOrder(null);
        if(!saveTaxist(taxist)) {
            return false;
        }
        Authority authority = new Authority();
        authority.setRolename(Authority.Rolename.ROLE_TAXIST);
        authority.setUser(taxist.getUser());
        HashSet<Authority> auth = new HashSet<>();
        auth.add(authority);
        taxist.getUser().setAuthorities(auth);
        return updateTaxist(taxist);
    }

    @Override
    public boolean saveTaxist(Taxist taxist) {
        try {
            taxistDAO.save(taxist);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteTaxist(Taxist taxist) {
        try {
            taxistDAO.delete(taxist);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public Taxist findByUser(User user) {
        return taxistDAO.findByUser(user);
    }

    @Override
    public boolean updateTaxist(Taxist taxist) throws DataAccessException {
        try {
            taxistDAO.update(taxist);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean setOffline(Taxist taxist) {
        taxist.setOnline(false);
        return updateTaxist(taxist);
    }

    @Override
    public boolean setOnline(Taxist taxist) {
        taxist.setOnline(true);
        return updateTaxist(taxist);
    }
}

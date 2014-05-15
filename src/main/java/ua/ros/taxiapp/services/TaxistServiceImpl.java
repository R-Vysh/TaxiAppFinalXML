package ua.ros.taxiapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.repository.TaxistDAO;

import java.io.Serializable;
import java.util.List;

@Service
public class TaxistServiceImpl implements TaxistService {
    @Autowired
    TaxistDAO taxistDAO;

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
    public boolean createNewTaxist(Taxist taxist) {
        try {
            taxistDAO.save(taxist);
        } catch (DataAccessException ex) {
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
}

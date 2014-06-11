package ua.ros.taxiapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ua.ros.taxiapp.domain.Model;
import ua.ros.taxiapp.repository.ModelDAO;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    ModelDAO modelDAO;

    public ModelDAO getModelDAO() {
        return modelDAO;
    }

    public void setModelDAO(ModelDAO modelDAO) {
        this.modelDAO = modelDAO;
    }

    @Override
    public boolean saveModel(Model model) {
        try {
            modelDAO.save(model);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public Model findById(Integer id) {
        return modelDAO.findByID(Model.class, id);
    }

    @Override
    public List<Model> getAllModels() {
        return modelDAO.findAll(Model.class);
    }

    @Override
    public boolean deleteModel(Model model) {
        try {
            modelDAO.delete(model);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateModel(Model model) {
        try {
            modelDAO.save(model);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public Model findByName(String name) {
        return modelDAO.findByName(name);
    }
}

package ua.ros.taxiapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ua.ros.taxiapp.domain.Brand;
import ua.ros.taxiapp.repository.BrandDAO;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDAO brandDAO;

    public BrandDAO getBrandDAO() {
        return brandDAO;
    }

    public void setBrandDAO(BrandDAO brandDAO) {
        this.brandDAO = brandDAO;
    }

    @Override
    public boolean createBrand(Brand brand) {
        try {
            brandDAO.save(brand);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public Brand findById(Integer id) {
        return brandDAO.findByID(Brand.class, id);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandDAO.findAll(Brand.class);
    }

    @Override
    public Brand findByName(String name) {
        return brandDAO.findByName(name);
    }

    @Override
    public boolean updateBrand(Brand brand) {
        try {
            brandDAO.save(brand);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteBrand(Brand brand) {
        try {
            brandDAO.delete(brand);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }
}

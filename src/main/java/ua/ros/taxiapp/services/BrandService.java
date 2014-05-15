package ua.ros.taxiapp.services;

import org.springframework.stereotype.Service;
import ua.ros.taxiapp.domain.Brand;

import java.util.List;

public interface BrandService {
    public boolean createBrand(Brand brand);

    public Brand findById(Integer id);

    public List<Brand> getAllBrands();

    public Brand findByName(String name);

    public boolean updateBrand(Brand brand);

    public boolean deleteBrand(Brand brand);
}

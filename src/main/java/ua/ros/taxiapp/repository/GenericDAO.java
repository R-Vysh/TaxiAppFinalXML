package ua.ros.taxiapp.repository;

import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {

    public void save(T entity) throws ConstraintViolationException;

    public void update(T entity) throws ConstraintViolationException;

    public void delete(T entity);

    public void merge(T entity);

    //    public T findOne(Query query);
    public List findAll(Class clazz);

    public T findByID(Class clazz, ID id);

}
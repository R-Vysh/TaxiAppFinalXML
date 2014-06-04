package ua.ros.taxiapp.repository;

import java.io.*;
import java.util.*;
import org.hibernate.Query;
 
public interface GenericDAO<T, ID extends Serializable> {
 
    public void save(T entity);
    public void update(T entity);
    public void delete(T entity);
//    public T findOne(Query query);
    public List findAll(Class clazz);
    public T findByID(Class clazz, ID id);

}
package ua.ros.taxiapp.repository.hibernate;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.ros.taxiapp.repository.GenericDAO;

public abstract class GenericDAOHibernate<T, ID extends Serializable> implements GenericDAO<T, ID> {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
 
    @Override
    public void save(T entity) {
        Session hibernateSession = this.getSession();
        Transaction tx =  hibernateSession.beginTransaction();
        hibernateSession.saveOrUpdate(entity);
        tx.commit();
    }
 
    @Override
    public void merge(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.merge(entity);
    }
 
    @Override
    public void delete(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.delete(entity);
    }
    
    @Override
    public T findOne(Query query) {
        T t;
        t = (T) query.uniqueResult();
        return t;
    }
 
    @Override
    @Transactional
    public T findByID(Class clazz, ID id) {
        Session hibernateSession = this.getSession();
        T t = null;
        t = (T) hibernateSession.get(clazz, id);
        return t;
    }
 
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<T> findAll(Class clazz) {
        Session hibernateSession = this.getSession();
        //hibernateSession.beginTransaction();
        List<T> t = null;
        Query query = hibernateSession.createQuery("from " + clazz.getName());
        t = query.list();
        //hibernateSession.getTransaction().commit();
        return t;
    }
}
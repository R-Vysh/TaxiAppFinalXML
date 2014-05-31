package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import ua.ros.taxiapp.repository.GenericDAO;

import java.io.Serializable;
import java.util.List;

public abstract class GenericDAOHibernate<T, ID extends Serializable> implements GenericDAO<T, ID> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional(rollbackFor={Exception.class})
    public void save(T entity) throws DataAccessException {
        Session session = this.getSession();
            session.save(entity);
    }

    @Override
    @Transactional
    public void merge(T entity) throws DataAccessException {
        Session hibernateSession = this.getSession();
        hibernateSession.merge(entity);
    }

    @Override
    @Transactional
    public void delete(T entity) throws DataAccessException {
        Session hibernateSession = this.getSession();
        hibernateSession.delete(entity);
    }

    @Override
    @Transactional
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
        List<T> t = null;
        Query query = hibernateSession.createQuery("from " + clazz.getName());
        t = query.list();
        return t;
    }
}
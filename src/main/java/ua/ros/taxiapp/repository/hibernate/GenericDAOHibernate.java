package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import ua.ros.taxiapp.repository.GenericDAO;

import java.io.Serializable;
import java.util.List;

public abstract class GenericDAOHibernate<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private static final Logger logger = LoggerFactory.getLogger(GenericDAOHibernate.class);

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    @Transactional(rollbackFor = {DataAccessException.class})
    public void save(T entity) throws DataAccessException {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    @Override
    @Transactional(rollbackFor = {DataAccessException.class})
    public void update(T entity) throws DataAccessException {
        Session hibernateSession = this.sessionFactory.getCurrentSession();
        hibernateSession.update(entity);
    }

    @Override
    @Transactional(rollbackFor = {DataAccessException.class})
    public void delete(T entity) throws DataAccessException {
        Session hibernateSession = this.sessionFactory.getCurrentSession();
        hibernateSession.delete(entity);
    }

    @Override
    @Transactional(rollbackFor = {DataAccessException.class})
    public void merge(T entity) throws DataAccessException {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(entity);
    }

    @Override
    @Transactional(rollbackFor = {DataAccessException.class})
    public T findByID(Class clazz, ID id) {
        Session hibernateSession = this.sessionFactory.getCurrentSession();
        T t = null;
        t = (T) hibernateSession.get(clazz, id);
        return t;
    }

    @Override
    @Transactional(rollbackFor = {DataAccessException.class})
    public List<T> findAll(Class clazz) {
        Session hibernateSession = this.sessionFactory.getCurrentSession();
        List<T> t = null;
        Query query = hibernateSession.createQuery("from " + clazz.getName());
        t = query.list();
        return t;
    }
}
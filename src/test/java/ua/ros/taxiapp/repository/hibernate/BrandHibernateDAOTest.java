package ua.ros.taxiapp.repository.hibernate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/persistenceContextTest.xml"})
public class BrandHibernateDAOTest extends RepositoryTestTemplate{

    @Autowired
    private BrandHibernateDAO brandDAO;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate.execute("TRUNCATE TABLE brands");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testFindByName() throws Exception {

    }

    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testFindByID() throws Exception {

    }

    @Test
    public void testFindAll() throws Exception {

    }
}

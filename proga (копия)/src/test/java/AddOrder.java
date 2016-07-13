import abstracts.AddEntities;
import abstracts.Base;
import categories.Add;
import entity.*;
import org.apache.struts.mock.MockHttpServletRequest;
import org.apache.struts.mock.MockHttpSession;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.remoting3.security.UserPrincipal;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import service.*;

import javax.ejb.EJB;
import javax.ejb.EJBException;

@RunWith(Arquillian.class)
public class AddOrder {

    @EJB
    OrderDao orderDao;

    @EJB
    UserDao userDao;

    @Deployment
    public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addClass(MockHttpServletRequest.class)
                .addClass(Base.class)
                .addClass(mapping.AddOrder.class)
                .addClass(Order.class)
                .addClass(OrderDao.class)
                .addClass(Drug.class)
                .addClass(DrugsDao.class)
                .addClass(AddEntities.class)
                .addClass(MockHttpSession.class)
                .addClass(Category.class)
                .addClass(CategoryDao.class)
                .addClass(User.class)
                .addClass(UserDao.class)
                .addClass(Role.class)
                .addClass(RoleDao.class)
                .addClass(Category.class)
                .addClass(CategoryDao.class)
                .addClass(UserPrincipal.class)
                .addPackages(true, "org.apache.commons.lang")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/persistence.xml",
                        "persistence.xml")
                ;
    }

    MockHttpServletRequest request = new MockHttpServletRequest();
    mapping.AddOrder addOrder;

    @Before
    public void before(){
        addOrder = new mapping.AddOrder();
        addOrder.setDao(orderDao, userDao);
    }

    @Test
    public void add1() throws EJBException {
        try{
            request.setUserPrincipal(new UserPrincipal("12"));
            request.addParameter("drug", "1");
            Assert.assertEquals(addOrder.add(request), "Выбрано");
        }
        catch (Exception error){

        }
    }
    @Test
    public void add2() throws EJBException {
        try{
            request.addParameter("drug", "1");
            Assert.assertEquals(addOrder.add(request), "Ошибка при передаче данных");
        }
        catch (Exception error){

        }
    }

    @Test
    public void add3() throws EJBException {
        try{
            request.addParameter("drug", "500'");
            request.setUserPrincipal(new UserPrincipal("12"));
            Assert.assertEquals(addOrder.add(request), "Невозможно выполнить запрос к базе");
        }
        catch (Exception error){

        }
    }
}

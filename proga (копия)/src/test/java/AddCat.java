import abstracts.AddEntities;
import abstracts.Base;
import categories.Add;
import entity.Category;
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
import service.CategoryDao;

import javax.ejb.EJB;
import javax.ejb.EJBException;

@RunWith(Arquillian.class)
public class AddCat {

    @EJB
    CategoryDao categoryDao;

    @Deployment
    public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addClass(MockHttpServletRequest.class)
                .addClass(Base.class)
                .addClass(Add.class)
                .addClass(AddEntities.class)
                .addClass(MockHttpSession.class)
                .addClass(Category.class)
                .addClass(CategoryDao.class)
                .addPackages(true, "org.apache.commons.lang")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/persistence.xml",
                        "persistence.xml")
                ;
    }

    MockHttpServletRequest request = new MockHttpServletRequest();
    Add addition;

    @Before
    public void before(){
        addition = new Add();
        addition.setCategoryDao(categoryDao);
    }

    @Test
    public void add1() throws EJBException {
        try{
            request.addParameter("title", "Глазные");
            Assert.assertEquals(addition.add(request), "Добавлено");
        }
        catch (Exception error){

        }
    }
    @Test
    public void add2() throws EJBException {
        try{
            request.addParameter("title", "");
            Assert.assertEquals(addition.add(request), "Неверный формат ввода");
        }
        catch (Exception error){

        }
    }

    @Test
    public void add3() throws EJBException {
        try{
            request.addParameter("title", "sdfsdf'");
            Assert.assertEquals(addition.add(request), "Неверный формат ввода");
        }
        catch (Exception error){

        }
    }
}

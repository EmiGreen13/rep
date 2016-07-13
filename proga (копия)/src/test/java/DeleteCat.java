import abstracts.AddEntities;
import abstracts.Base;
import abstracts.Deleting;
import categories.Add;
import categories.Delete;
import entity.Category;
import org.apache.struts.mock.MockHttpServletRequest;
import org.apache.struts.mock.MockHttpSession;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
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
public class DeleteCat {

    @EJB
    CategoryDao categoryDao;

    @Deployment
    public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addClass(MockHttpServletRequest.class)
                .addClass(Base.class)
                .addClass(Delete.class)
                .addClass(Deleting.class)
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
    Delete delete;

    @Before
    public void before(){
        delete = new Delete();
        delete.setCategoryDao(categoryDao);
    }

    @Test
    public void add1() throws EJBException {
        try{
            request.addParameter("id", "1");
            Assert.assertEquals(delete.delete(request), "Удалено");
        }
        catch (Exception error){

        }
    }
    @Test
    public void add2() throws EJBException {
        try{
            request.addParameter("id", "");
            Assert.assertEquals(delete.delete(request), "Неверный формат ввода");
        }
        catch (Exception error){

        }
    }

    @Test
    public void add3() throws EJBException {
        try{
            request.addParameter("id", "500'");
            Assert.assertEquals(delete.delete(request), "Неверный формат ввода");
        }
        catch (Exception error){

        }
    }
}

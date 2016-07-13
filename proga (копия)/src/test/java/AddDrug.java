import abstracts.AddEntities;
import abstracts.Base;
import drugs.Add;
import entity.Category;
import entity.Drug;
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
import service.DrugsDao;

import javax.ejb.EJB;
import javax.ejb.EJBException;

@RunWith(Arquillian.class)
public class AddDrug {

    @EJB
    DrugsDao drugsDao;

    @Deployment
    public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addClass(MockHttpServletRequest.class)
                .addClass(Base.class)
                .addClass(Add.class)
                .addClass(DrugsDao.class)
                .addClass(Drug.class)
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
        addition.setCategoryDao(drugsDao);
    }

    @Test
    public void add1() throws EJBException {
        try{
            request.addParameter("categoryId", "1");
            request.addParameter("title", "asdasd");
            request.addParameter("manufacture", "adasda");
            request.addParameter("country", "asdasd");
            request.addParameter("price", "12131");
            request.addParameter("description", "description");
            Assert.assertEquals(addition.add(request), "Добавлено");
        }
        catch (Exception error){

        }
    }

    @Test
    public void add2() throws EJBException {
        try{
            request.addParameter("categoryId", "56");
            request.addParameter("title", "asdasd");
            request.addParameter("manufacture", "adasda");
            request.addParameter("country", "asdasd");
            request.addParameter("price", "12131");
            request.addParameter("description", "description");
            Assert.assertEquals(addition.add(request), "Невозможно выполнить запрос к базе");
        }
        catch (Exception error){

        }
    }

    @Test
    public void add3() throws EJBException {
        try{
            request.addParameter("categoryId", "1");
            request.addParameter("title", "");
            request.addParameter("manufacture", "adasda");
            request.addParameter("country", "asdasd");
            request.addParameter("price", "12131");
            request.addParameter("description", "description");
            Assert.assertEquals(addition.add(request), "Ошибка формата данных");
        }
        catch (Exception error){

        }
    }

    @Test
    public void add4() throws EJBException {
        try{
            request.addParameter("categoryId", "1");
            request.addParameter("title", "fdsf");
            request.addParameter("manufacture", "adasda");
            request.addParameter("country", "asdasd");
            request.addParameter("price", "gdfg");
            request.addParameter("description", "description");
            Assert.assertEquals(addition.add(request), "Ошибка формата данных");
        }
        catch (Exception error){

        }
    }

    @Test
    public void add5() throws EJBException {
        try{
            request.addParameter("categoryId", "1");
            request.addParameter("title", "fdsf'");
            request.addParameter("manufacture", "adasd'a");
            request.addParameter("country", "asdasd");
            request.addParameter("price", "gdfg");
            request.addParameter("description", "description");
            Assert.assertEquals(addition.add(request), "Ошибка формата данных");
        }
        catch (Exception error){

        }
    }
}

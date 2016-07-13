import abstracts.Base;
import authentication.HashPassword;
import entity.Role;
import entity.User;
import mapping.Registration;
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
import service.RoleDao;
import service.UserDao;

import javax.ejb.EJB;
import javax.ejb.EJBException;


@RunWith(Arquillian.class)
public class TestRegistration {

    @EJB
    UserDao userDao;

    @EJB
    RoleDao roleDao;

    @Deployment
    public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addClass(MockHttpServletRequest.class)
                .addClass(HashPassword.class)
                .addClass(Registration.class)
                .addClass(Base.class)
                .addClass(MockHttpSession.class)
                .addClass(Role.class)
                .addClass(User.class)
                .addClass(UserDao.class)
                .addClass(UserPrincipal.class)
                .addClass(RoleDao.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/persistence.xml",
                        "persistence.xml")
                ;
    }

    private MockHttpServletRequest request = new MockHttpServletRequest();
    private MockHttpSession session = new MockHttpSession();
    private Registration registration;

    @Before
    public void before(){
        registration = new Registration();
        request.setHttpSession(session);
        registration.setDao(userDao, roleDao);
    }

    @Test
    public void existUser() throws EJBException {
        try {
            request.addParameter("username", "147");
            request.addParameter("password", "147");
            request.addParameter("password2", "147");
            request.addParameter("email", "st@fsdf.ru");
            registration.registration(request);
        }
        catch (Exception error){
            Assert.assertEquals(request.getSession().getAttribute("message"), "Пользователь с таким именем уже существует");
        }
    }

    @Test
    public void emptyFields() throws EJBException {
        try {
            request.addParameter("username", "");
            request.addParameter("password", "");
            request.addParameter("password2", "");
            request.addParameter("email", "");
            registration.registration(request);
        }
        catch (Exception error){
            Assert.assertEquals(request.getSession().getAttribute("message"), "Произошла ошибка");
        }
    }

    @Test
    public void wrongPassword() throws EJBException {
        try {
            request.addParameter("username", "32");
            request.addParameter("password", "12");
            request.addParameter("password2", "43");
            request.addParameter("email", "email@mail.ru");
            registration.registration(request);
        }
        catch (Exception error){
            Assert.assertEquals(request.getSession().getAttribute("message"), "Произошла ошибка");
        }
    }

   // @Test
    public void newUser() throws EJBException {
        try {
            request.addParameter("username", "2");
            request.addParameter("password", "2");
            request.addParameter("password2", "2");
            request.addParameter("email", "st@fsdf.ru");
            registration.registration(request);
        }
        catch (Exception error){
            Assert.assertEquals(request.getSession().getAttribute("message"), "Регистрация выполнена успешно");
        }
    }

    @Test
    public void afterLogin() throws EJBException {
        try {
            request.setUserPrincipal(new UserPrincipal("12"));
            request.addParameter("username", "2");
            request.addParameter("password", "2");
            request.addParameter("password2", "2");
            request.addParameter("email", "st@fsdf.ru");
            registration.registration(request);
        }
        catch (Exception error){
            Assert.assertEquals(request.getSession().getAttribute("message"), "Ошибка");
        }
    }

}
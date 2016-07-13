package mapping;

import abstracts.Base;
import authentication.HashPassword;
import entity.Role;
import entity.User;
import service.RoleDao;
import service.UserDao;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/registration")
public class Registration extends Base {

    @EJB(mappedName="EJBjava:global/test/UserDao!service.UserDao")
    UserDao userDao;

    @EJB(mappedName="EJBjava:global/test/RoleDao!service.RoleDao")
    RoleDao roleDao;

    public void setDao(UserDao userDao, RoleDao roleDao){
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/pages/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try{
            registration(request);
            response.sendRedirect("index");
        }
        catch (Exception exception){
            request.getRequestDispatcher("WEB-INF/pages/registration.jsp").forward(request, response);
        }
    }

    public void registration(HttpServletRequest request) throws NullPointerException, NumberFormatException, EJBException {
        if (request.getRemoteUser() != null){
            request.getSession(false).setAttribute("message", "Ошибка");
            throw new NullPointerException();
        }
        User user = User.createUser(request);
        if (user == null){
            request.getSession(false).setAttribute("message", "Произошла ошибка");
            throw new NullPointerException();
        }
        User userDatabase = userDao.findByUsername(user.getUsername());
        if (userDatabase != null){
            request.getSession(false).setAttribute("message", "Пользователь с таким именем уже существует");
            //return;//
            throw new EJBException();
        }
        String hashPassword = HashPassword.getHashPassword(user.getUsername(), user.getPassword());
        user.setPassword(hashPassword);
        userDao.save(user);
        userDatabase = userDao.findByUsername(user.getUsername());
        Role role = new Role(userDatabase.getId(), "User");
        roleDao.save(role);
        request.getSession(false).setAttribute("message", "Ошибка");
    }
}


package mapping;

import abstracts.AddEntities;
import entity.Drug;
import entity.User;
import service.DrugsDao;
import service.OrderDao;
import service.UserDao;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/select/order")
public class AddOrder extends AddEntities {

    @EJB
    OrderDao orderDao;

    @EJB
    UserDao userDao;

    protected String add(HttpServletRequest request) throws IndexOutOfBoundsException, SQLException, NullPointerException, EJBException {
        int id = new Integer(request.getParameter("drug"));
        String username = request.getRemoteUser();
        User user = userDao.findByUsername(username);
        orderDao.save(new entity.Order(id, user.getId()));
        return "Выбрано";
    }
}

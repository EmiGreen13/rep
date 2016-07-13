package mapping;

import abstracts.Selection;
import entity.Order;
import entity.User;
import service.OrderDao;
import service.UserDao;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/orders")
public class ViewOrder extends Selection {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            select(request);
        }catch (Exception exception){
            request.getSession().setAttribute("message", handle(exception));
        }
        request.getRequestDispatcher("WEB-INF/pages/vieworders.jsp").forward(request, response);
    }

    @EJB
    OrderDao orderDao;

    @EJB
    UserDao userDao;

    protected void select(HttpServletRequest request) throws IOException, IndexOutOfBoundsException, SQLException, NullPointerException, EJBException {
        String username = request.getRemoteUser();
        User user = userDao.findByUsername(username);
        List<Order> orders = orderDao.findAll(user.getId());
        request.setAttribute("orders", print(orders));
    }
}

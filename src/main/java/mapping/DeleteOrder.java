package mapping;

import abstracts.Deleting;
import service.OrderDao;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(urlPatterns = "/delete")
public class DeleteOrder extends Deleting {

    @EJB
    OrderDao orderDao;

    protected String delete(HttpServletRequest request) throws ParseException, NumberFormatException, SQLException, EJBException, NullPointerException {
        int id = new Integer(request.getParameter("id"));
        orderDao.delete(id);
        return "Удалено";
    }
}

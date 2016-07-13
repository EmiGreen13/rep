package drugs;

import abstracts.Deleting;
import service.CategoryDao;
import service.DrugsDao;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(urlPatterns = "/drugs/delete")
public class Delete extends Deleting {

    @EJB
    DrugsDao drugsDao;

    protected String delete(HttpServletRequest request) throws ParseException, NumberFormatException, SQLException, EJBException, NullPointerException {
        int id = new Integer(request.getParameter("id"));
        drugsDao.delete(id);
        return "Удалено";
    }
}

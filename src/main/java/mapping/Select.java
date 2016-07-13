package mapping;

import abstracts.Selection;
import service.DrugsDao;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(value = "/select")
public class Select extends Selection {

    @EJB
    DrugsDao drugsDao;

    protected void select(HttpServletRequest request) throws IOException, ParseException, IndexOutOfBoundsException, NamingException, SQLException, NullPointerException, EJBException {
        int id = new Integer(request.getParameter("id"));
        request.setAttribute("categoryId", id);
    }
}

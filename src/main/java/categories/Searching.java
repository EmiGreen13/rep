package categories;

import service.CategoryDao;

import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@WebServlet(value = {"/categories/search", "/getcategories"})
public class Searching extends abstracts.Searching {

    @EJB
    CategoryDao categoryDao;

    protected List search(HttpServletRequest request) throws ParseException, IOException, SQLException, NumberFormatException, NamingException, NullPointerException {
        return categoryDao.findAll();
    }
}

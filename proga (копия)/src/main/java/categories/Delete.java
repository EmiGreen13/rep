package categories;

import abstracts.AddEntities;
import abstracts.Deleting;
import entity.Category;
import service.CategoryDao;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(urlPatterns = "/categories/delete")
public class Delete extends Deleting {

    @EJB
    CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao){
        this.categoryDao = categoryDao;
    }

    public String delete(HttpServletRequest request) throws ParseException, NumberFormatException, SQLException, EJBException, NullPointerException {
        int id = new Integer(request.getParameter("id"));
        categoryDao.delete(id);
        return "Удалено";
    }
}

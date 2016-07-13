package categories;

import abstracts.AddEntities;
import abstracts.Edition;
import entity.Category;
import service.CategoryDao;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(urlPatterns = "/categories/edit")
public class Edit extends Edition {

    @EJB
    CategoryDao categoryDao;

    protected String edit(HttpServletRequest request) throws ParseException, NumberFormatException, SQLException, EJBException, NullPointerException {
        int id = new Integer(request.getParameter("id"));
        String title = request.getParameter("title");
        if (Category.titleMatches(title)){
            Category category = new Category(id, title);
            categoryDao.update(category);
            return "Изменено";
        }
        return "Не изменено";
    }

}

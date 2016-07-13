package categories;

import abstracts.AddEntities;
import entity.Category;
import service.CategoryDao;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(urlPatterns = "/categories/add")
public class Add extends AddEntities {

    @EJB
    CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao){
        this.categoryDao = categoryDao;
    }

    public String add(HttpServletRequest request) {//throws ParseException, NumberFormatException, SQLException, EJBException, NullPointerException {
        String title = request.getParameter("title");
        if (Category.titleMatches(title)){
            Category category = new Category(title);
            categoryDao.save(category);
            return "Добавлено";
        }
        return "Неверный формат ввода";
    }

}

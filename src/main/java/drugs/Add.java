package drugs;

import abstracts.AddEntities;
import entity.Category;
import entity.Drug;
import service.CategoryDao;
import service.DrugsDao;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(urlPatterns = "/drugs/add")
public class Add extends AddEntities {

    @EJB
    DrugsDao drugsDao;

    protected String add(HttpServletRequest request){//} throws ParseException, NumberFormatException, SQLException, EJBException, NullPointerException {
        Drug drug = Drug.createDrug(request);
        drugsDao.save(drug);
        return "Добавлено";
    }

}

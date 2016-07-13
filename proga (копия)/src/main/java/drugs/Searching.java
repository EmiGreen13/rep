package drugs;

import entity.Drug;
import org.apache.commons.lang.math.NumberUtils;
import service.CategoryDao;
import service.DrugsDao;

import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = {"/drugs/search", "/getdrugs"})
public class Searching extends abstracts.Searching {

    @EJB
    DrugsDao drugsDao;

    protected List search(HttpServletRequest request) throws ParseException, IOException, SQLException, NumberFormatException, NamingException, NullPointerException {
        String param = request.getParameter("param");
        int categoryId = new Integer(request.getParameter("categoryId"));
        if(NumberUtils.isNumber(param)){
            return drugsDao.findByNumber(new Integer(param), categoryId);
        }
        if (param.length() != 0){
            if (Drug.mainMatches(param)){
                return drugsDao.findByTitle(param, categoryId);
            }
            else{
                throw new NumberFormatException();
            }
        }
        ArrayList arrayList = drugsDao.findAll(categoryId);
        return drugsDao.findAll(categoryId);
    }
}

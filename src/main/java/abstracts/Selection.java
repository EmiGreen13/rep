package abstracts;

import javax.ejb.EJBException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet
public abstract class Selection extends Base {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            select(request);
        }catch (Exception exception){
            request.getSession().setAttribute("message", handle(exception));
        }
        request.getRequestDispatcher("WEB-INF/pages/select.jsp").forward(request, response);
    }
    protected abstract void select(HttpServletRequest request) throws IOException, ParseException, IndexOutOfBoundsException, NamingException, SQLException, NullPointerException, EJBException;
}

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
public abstract class Edition extends Base{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            print(edit(request), response);
        }
        catch (Exception exception){
            response.setStatus(500);
            print(handle(exception), response);
        }
    }

    protected abstract String edit(HttpServletRequest request)throws ParseException, NumberFormatException, SQLException, EJBException, NullPointerException;
}

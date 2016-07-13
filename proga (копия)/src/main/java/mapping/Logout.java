package mapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@WebServlet(value = "/j_security_logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        invalidateSession(request);
        String url = redirectedURL(request);
        response.sendRedirect(url);
    }

    private void invalidateSession(HttpServletRequest request){
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                request.logout();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @NotNull
    private String redirectedURL(HttpServletRequest request){
        String url = request.getHeader("referer");
        if (url == null){
            url = "index";
        }
        return url;
    }
}

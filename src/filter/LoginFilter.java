package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @version v1.0
 * @ClassName: ${NAME}
 * @Description: ${DESCRIPTION}
 * @Author: ChenQ
 * @Date: 2021/11/16 on 21:19
 */
@WebFilter(filterName = "LoginFilter",urlPatterns = "/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest hreq = (HttpServletRequest) req;
        HttpServletResponse hresp = (HttpServletResponse) resp;
        HttpSession session = hreq.getSession();
        if (session.getAttribute("user")!=null){
            chain.doFilter(req, resp);
        }else if (hreq.getRequestURI().indexOf("loginForm.jsp")>=0
                ||(hreq.getContextPath()+"/form/Login").equals(hreq.getRequestURI())
                ||hreq.getRequestURI().indexOf("registerForm.jsp")>=0
                ||(hreq.getContextPath()+"/form/Register").equals(hreq.getRequestURI())
        ){
            chain.doFilter(req,resp);
        }else {
            hresp.sendRedirect(hreq.getContextPath()+"/form/loginForm.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

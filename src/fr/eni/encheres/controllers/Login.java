package fr.eni.encheres.controllers;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.LoginForm;
import fr.eni.encheres.models.bll.ManagerFactory;
import fr.eni.encheres.models.bo.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String ATT_USER = "user";
    public static final String ATT_FORM = "form";
    public static final String ATT_SESSION_USER = "sessionUser";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//On récupère les cookies
    	Cookie[] cookies = request.getCookies();
    	if(cookies != null) {
    		
    		for (Cookie cookie : cookies) {
    			if(cookie.getName().equals("log")) {
    				request.setAttribute("login", cookie.getValue());
    			}
			}
    	}
    	
        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BusinessException exceptionList = new BusinessException();
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        LoginForm form = ManagerFactory.getLoginForm(exceptionList);
        User user = new User();

        try {
            user = form.connectUser(login, password);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        
        //On set les cookies pour un Remember me
        String rememberMe = request.getParameter("remember");
        
       if(rememberMe != null) {
        	 Cookie cookie = new Cookie("log", login);
             cookie.setMaxAge(60*60*60*24*30);
             response.addCookie(cookie);
        }
       
        
        if (exceptionList.hasErrors()) {
			request.setAttribute("listeError", exceptionList.getErrorCodesList());
            request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("idUser", user.getIdUser());
            session.setAttribute("user", user);
            response.sendRedirect("index");
        }
        
       
        
    }
}

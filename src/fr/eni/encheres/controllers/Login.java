package fr.eni.encheres.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.LoginForm;
import fr.eni.encheres.models.bll.ManagerFactory;
import fr.eni.encheres.models.bll.user.UserManager;
import fr.eni.encheres.models.bo.User;
import fr.eni.encheres.models.dal.user.UserDAOJdbcImpl;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER         = "user";
	public static final String ATT_FORM         = "form";
	public static final String ATT_SESSION_USER = "sessionUser";

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
		/* Affichage de la page de connexion */
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response );
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        LoginForm form = new LoginForm();
        HttpSession session = request.getSession();

        /*
         *  Récupération des saisies
         */
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
        User user = null;
		try {
			user = form.connectUser(login, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
        /*if ( form.getErrors().isEmpty() ) {
            session.setAttribute( ATT_SESSION_USER, user );
        } else {
            session.setAttribute( ATT_SESSION_USER, null );
        } */
        if ( user != null ) {
        	session.setAttribute("id_user", user.getIdUser());
        	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward( request, response );
        } else {
        	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response );
        }
                
        /* Stockage du formulaire et du bean dans l'objet request 
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, user );
        */
       
    }
    
    

}

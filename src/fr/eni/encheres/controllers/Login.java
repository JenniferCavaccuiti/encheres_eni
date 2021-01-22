package fr.eni.encheres.controllers;

import java.io.IOException;
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
		List<User> users = null;
		try {
			users = ManagerFactory.getUserManager().findAll();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Les stoquer en attributs
		request.setAttribute("users", users);
		System.out.println(users);
		

		/* Affichage de la page de connexion */
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response );
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        LoginForm form = new LoginForm();

        /* Traitement de la requête et récupération du bean en résultant */
        User user = form.connectUser(request);

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        
        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
        if ( form.getErrors().isEmpty() ) {
            session.setAttribute( ATT_SESSION_USER, user );
        } else {
            session.setAttribute( ATT_SESSION_USER, null );
        }

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, user );

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward( request, response );
    }

}

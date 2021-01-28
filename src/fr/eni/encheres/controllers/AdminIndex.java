package fr.eni.encheres.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.ManagerFactory;
import fr.eni.encheres.models.bo.User;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/administration-accueil")
public class AdminIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User> users = new ArrayList<>();
		
		//On récupère la liste des utilisateurs
		try {
			users = ManagerFactory.getUserManager().findAll();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	
		request.setAttribute("users", users);
		request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String supprimer = request.getParameter("supprimer");
		String desactiver = request.getParameter("desactiver");
		
		if(supprimer != null) {
			request.setAttribute("simpleUser", supprimer);
			request.setAttribute("supprimer", "supprimer");
			request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/adminActions.jsp").forward(request, response);
		}
		
		if(desactiver != null) {
			request.setAttribute("simpleUser", desactiver);
			request.setAttribute("desactiver", "desactiver");
			request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/adminActions.jsp").forward(request, response);
		}
		
	}

}

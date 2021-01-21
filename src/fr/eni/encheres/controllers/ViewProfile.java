package fr.eni.encheres.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.ManagerFactory;
import fr.eni.encheres.models.bo.User;

/**
 * Servlet implementation class ViewProfile
 */
@WebServlet("/profil-utilisateur")
public class ViewProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = null;
		String login = request.getParameter("login");
		
		try {
			user = ManagerFactory.getUserManager().selectUserByLog(login);
		} catch (BusinessException e) {
			e.printStackTrace();
			
		}
		
		
		request.setAttribute("userProfile", user);
		request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/profileView.jsp").forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

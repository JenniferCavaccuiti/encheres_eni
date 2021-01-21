package fr.eni.encheres.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.models.bll.ManagerFactory;
import fr.eni.encheres.models.bo.User;

/**
 * Servlet implementation class ViewProfile
 */
@WebServlet("/ViewProfile")
public class ViewProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = null;
		user = ManagerFactory.getUserManager().selectUserByLogin(login);
		user = new User("toto", "lastname", "firstname", "email", "phoneNumber", "street", "postalCode", "city", "password", 100, false); 
		
		request.setAttribute("userProfile", user);
		request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/profileView.jsp").forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

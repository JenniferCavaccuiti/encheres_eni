package fr.eni.encheres.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.messages.MessagesReader;
import fr.eni.encheres.models.bll.user.UserManager;

/**
 * Servlet implementation class CreateProfil
 */
@WebServlet(name = "nouveauProfil", value = "/nouveau-profil")
public class CreateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/createProfile.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String phoneNumber = request.getParameter("phoneNumber");
		String postalCode = request.getParameter("postalCode");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String confirm = request.getParameter("passwordConfirm");
			
		UserManager userManager = new UserManager();
		
		System.out.println("xxxxxxxx");
		
		try {
			userManager.addUser(login, lastname, firstname, email, phoneNumber, street, postalCode, city, password, confirm, false);
			request.getSession().setAttribute("login", login);
			request.getServletContext().getRequestDispatcher("/index").forward(request, response);
			
		} catch (BusinessException e) {
			e.printStackTrace();
			e.getErrorCodesList();
			request.setAttribute("liste", e.getErrorCodesList());
			
			System.out.println(e.getErrorCodesList());
			
			for (int z : e.getErrorCodesList()) {
				
				MessagesReader.getErrorMessage(z);
				System.out.println(MessagesReader.getErrorMessage(z));
			}
			
			request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/createProfile.jsp").forward(request, response);
		}
	
		System.out.println("xxxxxxxx");
		
		
		
	}
		
	}



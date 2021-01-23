package fr.eni.encheres.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.messages.MessagesReader;
import fr.eni.encheres.models.bll.ManagerFactory;
import fr.eni.encheres.models.bll.user.UserManager;
import fr.eni.encheres.models.bo.User;

/**
 * Servlet implementation class UpdateProfile
 */
@WebServlet("/modifier-profil")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/updateProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String street = request.getParameter("street");
		String postalCode = request.getParameter("postalCode");
		String city = request.getParameter("city");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirm = request.getParameter("passwordConfirm");
		
		//On récupère les infos de l'user via son login stocké en session
		//String loginSession = (String) request.getSession().getAttribute("login");
		String loginSession = "OKKKK";
		User user = null;
		UserManager userManager = new UserManager();
	
		try {
			user = ManagerFactory.getUserManager().selectUserByLog(loginSession);
			userManager.updateUser(user, login, lastname, firstname, email, phoneNumber, street, postalCode, city, oldPassword, newPassword, confirm);
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
			
			
			
			request.setAttribute("credit", user.getCredits());
			request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/updateProfile.jsp").forward(request, response);
			
		}
		
				
		System.out.println("xxxxxxxx");
				
	}

}
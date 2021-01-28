package fr.eni.encheres.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.ManagerFactory;
import fr.eni.encheres.models.bo.Item;
import fr.eni.encheres.models.bo.User;

/**
 * Servlet implementation class AdminActions
 */
@WebServlet("/administration-actions")
public class AdminActions extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//On récupère l'user concerné par l'action d'admin
		String login = request.getParameter("login");
		User user = null;
		try {
			user = ManagerFactory.getUserManager().selectUserByLog(login);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		String action = request.getParameter("action");
		
		if(action.equals("deactivate")) {
			
			try {
				List<Item> itemLists = ManagerFactory.getItemManager().selectItemByUser(user); //On récupère la liste de tous les articles correspondants à l'user
				for (Item item : itemLists) {
					ManagerFactory.getBidManager().deleteBidByItem(item);//On suppr les enchères faites sur les articles de l'user
				}
				ManagerFactory.getBidManager().deleteBidByIdBuyer(user); //On suppr les enchères de l'user
				ManagerFactory.getItemManager().deleteItemByIdSeller(user); //On suppr les articles de l'user
				user.setAdministrator(null);
				user = ManagerFactory.getUserManager().updateUser(user); //On met à jour l'user avec son nouveau statut
				
			} catch (BusinessException e) {
				e.printStackTrace();
			} 
			
			response.sendRedirect("administration-accueil");

		}
		
		if(action.equals("delete")) {
			try {
				List<Item> itemLists = ManagerFactory.getItemManager().selectItemByUser(user); 
				
				for (Item item : itemLists) {
					ManagerFactory.getBidManager().deleteBidByItem(item);
				}
				ManagerFactory.getBidManager().deleteBidByIdBuyer(user); 
				ManagerFactory.getItemManager().deleteItemByIdSeller(user); 
				ManagerFactory.getUserManager().deleteUser(user); //On suppr l'user
				
				
			} catch (BusinessException e) {
				e.printStackTrace();
				e.getErrorCodesList();
				request.setAttribute("liste", e.getErrorCodesList());
			}
			
			response.sendRedirect("administration-accueil");
		}
		
	}

}

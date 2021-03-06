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
 * Servlet implementation class DeleteProfile
 */
@WebServlet("/supprimer-profil")
public class DeleteProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Ne pas autoriser l'accès à la page en cas de déconnexion
		boolean connected = BaseController.isConnected(request.getSession().getAttribute("user"));
			       
		        if (!connected) {
					response.sendRedirect(request.getContextPath()+"/index");
		        } else {
		        	request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/deleteProfile.jsp").forward(request, response);
		        }
		       
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		
			try {
				
				List<Item> itemLists = ManagerFactory.getItemManager().selectItemByUser(user); //On récupère la liste de tous les articles correspondants à l'user
				
				for (Item item : itemLists) {
					ManagerFactory.getBidManager().deleteBidByItem(item);//On suppr les enchères faites sur les articles de l'user
				}
				ManagerFactory.getBidManager().deleteBidByIdBuyer(user); //On suppr les enchères de l'user
				ManagerFactory.getItemManager().deleteItemByIdSeller(user); //On suppr les articles de l'user
				ManagerFactory.getUserManager().deleteUser(user);
				request.getSession().invalidate();
				response.sendRedirect(request.getContextPath()+"/index");

			} catch (BusinessException e) {
				e.printStackTrace();
				e.getErrorCodesList();
				request.setAttribute("liste", e.getErrorCodesList());
				System.out.println(e.getErrorCodesList());
				request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/deleteProfile.jsp").forward(request, response);
			}	
		}		
	}



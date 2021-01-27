package fr.eni.encheres.controllers;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.ManagerFactory;
import fr.eni.encheres.models.bo.Bid;
import fr.eni.encheres.models.bo.Item;
import fr.eni.encheres.models.bo.User;

/**
 * Servlet implementation class SaleDetails
 */
@WebServlet("/details-vente")
public class SaleDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recupération du paramètre placé en URL
		int id = Integer.parseInt(request.getParameter("itemId"));
		Item item = null;

		// On récupère l'item
		try {
			item = ManagerFactory.getItemManager().getItemById(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		// On cherche la catégorie correspondante à l'idCategory
		// On cherche le vendeur correspondant à l'idSeller
		item = ManagerFactory.getItemManager().setSellerNameCategoryName(item);

		// On cherche le login du plus gros encherisseur
		String login = null;

		try {
			login = ManagerFactory.getBidManager().biggestBuyer(item);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		// User user = (User) request.getSession().getAttribute("user");

		boolean beforeEndBid = LocalDateTime.now().isBefore(item.getBidsEndDate());
		boolean afterStartBid = LocalDateTime.now().isAfter(item.getBidsStartDate());

		request.setAttribute("beforeEnd", beforeEndBid);
		request.setAttribute("afterStart", afterStartBid);
		request.setAttribute("item", item);
		request.setAttribute("login", login);
		System.out.println(login);

		// Ne pas autoriser l'accès à la page en cas de déconnexion
		boolean connected = BaseController.isConnected(request.getSession().getAttribute("user"));

		if (!connected) {
			response.sendRedirect(request.getContextPath() + "/index");
		} else {
			request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/saleDetails.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recupération de l'item
		int id = Integer.parseInt(request.getParameter("idItem"));
		Item item = null;
		
		try {
			item = ManagerFactory.getItemManager().getItemById(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		item = ManagerFactory.getItemManager().setSellerNameCategoryName(item);
		boolean beforeEndBid = LocalDateTime.now().isBefore(item.getBidsEndDate());
		boolean afterStartBid = LocalDateTime.now().isAfter(item.getBidsStartDate());
		request.setAttribute("beforeEnd", beforeEndBid);
		request.setAttribute("afterStart", afterStartBid);
		request.setAttribute("item", item);
		
		//On récupère l'enchérisseur actuel & le prix qu'il propose
		User user = (User) request.getSession().getAttribute("user");
		int bidderPrice = Integer.parseInt(request.getParameter("bidderPrice"));
				
		
		// On cherche l'user qui était le plus gros enchérisseur avant
		User lastBidder = null;

		try {
			lastBidder = ManagerFactory.getUserManager().selectUserByLog(request.getParameter("login"));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		if(lastBidder == null) { //Dans le cas où l'user serait le premier enchérisseur
			lastBidder = user;
		}
		
		//On teste si l'enchère est possible
		BusinessException exception = new BusinessException();
		try {
			
			boolean isPossible = ManagerFactory.getBidManager().bidIsPossible(user, item, bidderPrice, exception);
			
			if(isPossible == true) {
				
				//On insère l'enchère en BDD
				Bid bid = new Bid(user.getIdUser(), item.getIdItem(), LocalDateTime.now(), bidderPrice);
				bid = ManagerFactory.getBidManager().insertBid(bid);
				
				//On update les users - on débite/crédite le crédit
				
				if(lastBidder.getIdUser() == user.getIdUser()) {
					user.setCredits(user.getCredits() - bidderPrice + item.getCurrentPrice()); 
					user = ManagerFactory.getUserManager().updateUser(user);
					System.out.println(user);
					//request.getSession().setAttribute("user", user);
				}
				
				if(!(lastBidder.getIdUser() == user.getIdUser())) {
					user.setCredits(user.getCredits() - bidderPrice);
					user = ManagerFactory.getUserManager().updateUser(user);
					lastBidder.setCredits(item.getCurrentPrice() + lastBidder.getCredits()); //On recrédite l'ancien enréchisseur de son offre
					lastBidder = ManagerFactory.getUserManager().updateUser(lastBidder); //On le met à jour en BDD
					//request.getSession().setAttribute("user", user);
				}
				
				//On met à jour l'item
				item.setCurrentPrice(bidderPrice); //update l'item en BDD
				item = ManagerFactory.getItemManager().updateItemById(item);
				
				response.sendRedirect("index");
				
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
			e.getErrorCodesList();
			request.setAttribute("liste", e.getErrorCodesList());
			request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/saleDetails.jsp").forward(request, response);
			}

	}

}

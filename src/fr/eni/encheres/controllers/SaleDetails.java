package fr.eni.encheres.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.ManagerFactory;
import fr.eni.encheres.models.bo.Item;

/**
 * Servlet implementation class SaleDetails
 */
@WebServlet("/details-vente")
public class SaleDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recupération du paramètre placé en URL
		int id = Integer.parseInt(request.getParameter("itemId"));
		
		Item item = null;
		
		//On récupère l'item
		try {
			item = ManagerFactory.getItemManager().getItemById(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		//On cherche la catégorie correspondante à l'idCategory		
		//On cherche le vendeur correspondant à l'idSeller
		item= ManagerFactory.getItemManager().setSellerNameCatagoryName(item);
		System.out.println(item);
		System.out.println(item.getCategoryName());
		
		request.setAttribute("item", item);
				
		request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/saleDetails.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

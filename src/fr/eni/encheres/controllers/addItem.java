package fr.eni.encheres.controllers;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.ManagerFactory;
import fr.eni.encheres.models.bo.Item;
import fr.eni.encheres.models.bo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@WebServlet(name = "nouvelle-vente", value = "/nouvelle-vente")
public class addItem extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean connected = BaseController.isConnected(request.getSession().getAttribute("idUser"));
        
        User user = (User) request.getSession().getAttribute("user");

        System.out.println(request.getParameter("itemId"));

        if (!connected) {
            response.sendRedirect(request.getContextPath() + "/index");
        } 
        
        else if (connected && user.getAdministrator().equals(null)) {
        	response.sendRedirect(request.getContextPath() + "/index");
        }
        
        else {
            if (request.getParameter("itemId") != null) {
                int idItem = Integer.parseInt(request.getParameter("itemId"));
                Item item = null;
                try {
                    item = ManagerFactory.getItemManager().getItemById(idItem);
                    item.setCategoryName(ManagerFactory.getCategoryManager().findOneById(item.getIdCategory()));

                } catch (BusinessException businessException) {
                    businessException.printStackTrace();
                }
                request.setAttribute("item", item);
            }

            try {
                request.setAttribute("categoriesList", BaseController.getCategoriesList());
            } catch (BusinessException | SQLException businessException) {
                businessException.printStackTrace();
            }
            request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/addItem.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean connected = BaseController.isConnected(request.getSession().getAttribute("idUser"));
        Item item = null;
        int isDelete = 0;
        if (!connected) {
            response.sendRedirect("index");
        } else {
            Map<String, String[]> parameters = new HashMap<>(request.getParameterMap());
            HttpSession session = request.getSession();

            String choiceType = request.getParameter("submitType");

            switch (choiceType) {
                case "update" :
//                    Boolean startBids = BaseController.isAfterStartBid(LocalDateTime.now(), LocalDateTime.parse(parameters.get("bidsStartDate")[0]));
//                    System.out.println("isAfterStart" + startBids);
//                    request.setAttribute("startBid", startBids);
                    try {
                        item = ManagerFactory.getItemManager().updateItem(parameters, (Integer) request.getSession().getAttribute("idUser"));
                    } catch (BusinessException businessException) {
                        businessException.printStackTrace();
                    }
                    session.setAttribute("message", "Modification enregistrée.");
                    break;
                case "delete" :
                    int idItem = Integer.parseInt(request.getParameter("idItem"));
                    isDelete = ManagerFactory.getItemManager().deleteItem(idItem);
                    session.setAttribute("message", "Votre vente a été supprimée.");
                    break;
                case "create" :
                    item = ManagerFactory.getItemManager().addItem(parameters, (Integer) request.getSession().getAttribute("idUser"));
                    session.setAttribute("message", "Nouvelle vente enregistrée.");
                    break;
            }


            if (item != null || isDelete == 1) {
                response.sendRedirect("index");
            } else {
                request.setAttribute("message", "Impossible d'effectuer la demande.");
                request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/addItem.jsp").forward(request, response);
            }
        }
    }

    public void redirection(String message, String jsp) {


    }
}


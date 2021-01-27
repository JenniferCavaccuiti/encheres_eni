package fr.eni.encheres.controllers;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.ManagerFactory;
import fr.eni.encheres.models.bo.Item;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "nouvelle-vente", value = "/nouvelle-vente")
public class addItem extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean connected = BaseController.isConnected(request.getSession().getAttribute("idUser"));

        System.out.println(request.getParameter("itemId"));

        if (!connected) {
            response.sendRedirect(request.getContextPath() + "/index");
        } else {
            if (request.getParameter("itemId") != null) {
                System.out.println("j'arrive au bon endroit");
                int idItem = Integer.parseInt(request.getParameter("itemId"));
                System.out.println(request.getAttribute("itemId"));
                Item item = null;
                try {
                    item = ManagerFactory.getItemManager().getItemById(idItem);
                    System.out.println(item);
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
        if (!connected) {
            response.sendRedirect("index");
        } else {
            Map<String, String[]> parameters = new HashMap<>(request.getParameterMap());

            Item item = ManagerFactory.getItemManager().addItem(parameters, (Integer) request.getSession().getAttribute("idUser"));
            HttpSession session = request.getSession();
            if (item != null) {
                session.setAttribute("message", "Nouvelle vente enregistrée.");
                response.sendRedirect("index");
            } else {
                request.setAttribute("message", "Impossible d'enregistrer la nouvelle vente.");
                request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/addItem.jsp").forward(request, response);
            }
        }
    }
}


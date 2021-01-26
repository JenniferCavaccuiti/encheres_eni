package fr.eni.encheres.controllers;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.ManagerFactory;
import fr.eni.encheres.models.bo.Category;
import fr.eni.encheres.models.bo.Item;

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

        if (!connected) {
            response.sendRedirect("index");
        }

        try {
            request.setAttribute("categoriesList", BaseController.getCategoriesList());
        } catch (BusinessException | SQLException businessException) {
            businessException.printStackTrace();
        }

        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/addItem.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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


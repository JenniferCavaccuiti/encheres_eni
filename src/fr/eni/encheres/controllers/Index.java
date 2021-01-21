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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "index", value = "/index")
public class Index extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Item> itemsList = null;
        List<User> usersList = null;
        ManagerFactory.getItemManager();
        ManagerFactory.getUserManager();

        String searchedWord = request.getParameter("searchedWord");

        try {
//            Récupération des users et des items en base
            itemsList = ManagerFactory.getItemManager().findAll();
            usersList = ManagerFactory.getUserManager().findAll();

//            barre de recherche : si un mot clé est rentré, récupérer les items dont le nom contient le mot
//             TODO enlever les espaces et les majuscules de la recherche
            if (searchedWord != null) {
                itemsList = ManagerFactory.getItemManager().searchedItems(searchedWord, itemsList);
            }

        } catch (BusinessException | SQLException businessException) {
            businessException.printStackTrace();
        }

        request.setAttribute("itemsList", itemsList);
        request.setAttribute("usersList", usersList);

        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

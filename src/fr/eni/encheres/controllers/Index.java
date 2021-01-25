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
import java.util.List;

@WebServlet(name = "index", value = "/index")
public class Index extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Item> itemsList = null;
        List<Category> categoriesList = null;

        boolean connected = isConnected(request.getSession().getAttribute("idUser"));


        try {
            // mode déco marche pas date enchères
            if (!connected) {
                itemsList = ManagerFactory.getItemManager().findOnGoingItems();
            } else {
                itemsList = ManagerFactory.getItemManager().findAll();
            }
            categoriesList = ManagerFactory.getCategoryManager().findAll();

        } catch (BusinessException | SQLException businessException) {
            businessException.printStackTrace();
        }

        // TODO la liste de catégories ne s'affiche pas en mode connecté

        request.setAttribute("itemsList", itemsList);
        request.setAttribute("categoriesList", categoriesList);

        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Item> itemsList = null;
        List<Category> categoriesList = null;

        String searchedWord = request.getParameter("searchedWord");
        String searchedCategory = request.getParameter("searchedCategory");
        String filter = request.getParameter("filter");
        int idUser;

        boolean connected = isConnected(request.getSession().getAttribute("idUser"));


        try {
            // si user pas connecté
            if (!connected) {
                    itemsList = ManagerFactory.getItemManager().getNonConnectedList(searchedWord, searchedCategory);
            } else {
                idUser = (int) request.getSession().getAttribute("idUser");

                // checkbox
                if (filter != null) {
                    itemsList = ManagerFactory.getItemManager().searchedItemsByFilter(filter, idUser);
                } else {
                    itemsList = ManagerFactory.getItemManager().findAll();
                }

                // barre de recherche
//                if (searchedWord != null || searchedCategory != null) {
//                    itemsList = ManagerFactory.getItemManager().connectedSearch(searchedWord, searchedCategory, itemsList);
//                }
            }

            categoriesList = ManagerFactory.getCategoryManager().findAll();
        } catch (BusinessException | SQLException businessException) {
            businessException.printStackTrace();
        }

        request.setAttribute("itemsList", itemsList);
        request.setAttribute("categoriesList", categoriesList);

        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }

    public boolean isConnected(Object idUser) {
        return idUser != null;

    }
}

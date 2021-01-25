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
            if (!connected) {
                itemsList = ManagerFactory.getItemManager().findOnGoingItems();
            } else {
                itemsList = ManagerFactory.getItemManager().findAll();
            }
            categoriesList = ManagerFactory.getCategoryManager().findAll();

        } catch (BusinessException | SQLException businessException) {
            businessException.printStackTrace();
        }

        request.setAttribute("itemsList", itemsList);
        request.setAttribute("categoriesList", categoriesList);

        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Item> itemsList = null;
        List<Category> categoriesList = null;

        String keyword = request.getParameter("keyword");
        String searchedCategory = request.getParameter("searchedCategory");
        String filter = request.getParameter("filter");
        int idUser;

        // renvoi si user est connecté
        boolean isConnected = isConnected(request.getSession().getAttribute("idUser"));

        try {
            if (!isConnected) {
                itemsList = ManagerFactory.getItemManager().getNonConnectedList(keyword, searchedCategory);
            } else {
                idUser = (int) request.getSession().getAttribute("idUser");
                if (filter == null) {
                    itemsList = ManagerFactory.getItemManager().getConnectedList(keyword, searchedCategory);
                } else {
                    itemsList = ManagerFactory.getItemManager().searchedItemsByFilter(filter, idUser);
                }
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

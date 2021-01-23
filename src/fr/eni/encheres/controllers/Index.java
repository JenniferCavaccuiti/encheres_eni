package fr.eni.encheres.controllers;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.ManagerFactory;
import fr.eni.encheres.models.bo.Category;
import fr.eni.encheres.models.bo.Item;
import fr.eni.encheres.models.bo.User;

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
        List<User> usersList = null;
        List<Category> categoriesList = null;

        try {
            itemsList = ManagerFactory.getItemManager().findAll();
            usersList = ManagerFactory.getUserManager().findAll();
            categoriesList = ManagerFactory.getCategoryManager().findAll();

        } catch (BusinessException | SQLException businessException) {
            businessException.printStackTrace();
        }
        HttpSession session = request.getSession();
        System.out.println(itemsList);

        session.setAttribute("idUser", 3);
        request.setAttribute("itemsList", itemsList);
        request.setAttribute("usersList", usersList);
        request.setAttribute("categoriesList", categoriesList);

        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Item> itemsList = null;
        List<User> usersList = null;
        List<Category> categoriesList = null;
        int userId = 3;

        String searchedWord = request.getParameter("searchedWord");
        String searchedCategory = request.getParameter("searchedCategory");
        String filter = request.getParameter("filter");
        System.out.println(filter);

        try {
            itemsList = ManagerFactory.getItemManager().findAll();
            usersList = ManagerFactory.getUserManager().findAll();
            categoriesList = ManagerFactory.getCategoryManager().findAll();

            if (searchedWord != null || searchedCategory != null) {
                itemsList = ManagerFactory.getItemManager().searchedItems(searchedWord, itemsList, searchedCategory);
            }
            if (filter != null) {
                itemsList = ManagerFactory.getItemManager().searchedItemsByFilter(filter, userId);
            }

        } catch (BusinessException | SQLException businessException) {
            businessException.printStackTrace();
        }

        request.setAttribute("itemsList", itemsList);
        request.setAttribute("usersList", usersList);
        request.setAttribute("categoriesList", categoriesList);

        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }
}

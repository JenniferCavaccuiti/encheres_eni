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
import java.util.List;

@WebServlet(name = "index", value = "/index")
public class Index extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        List<Item> itemsList = null;
        List<User> usersList = null;
        ManagerFactory.getItemManager();
        ManagerFactory.getUserManager();
        try {
            itemsList = ManagerFactory.getItemManager().findAll();
            usersList = ManagerFactory.getUserManager().findAll();
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

package fr.eni.encheres.controllers;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.ManagerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "index", value = "/index")
public class Index extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean connected = BaseController.isConnected(request.getSession().getAttribute("idUser"));
        HttpSession session = request.getSession();

        try {
            if (!connected) {
                request.setAttribute("itemsList", ManagerFactory.getItemManager().findOnGoingItems());
            } else {
                request.setAttribute("itemsList", ManagerFactory.getItemManager().findAll());
            }
            request.setAttribute("categoriesList", BaseController.getCategoriesList());
        } catch (BusinessException | SQLException businessException) {
            businessException.printStackTrace();
        }
        request.setAttribute("message", request.getSession().getAttribute("message"));
        session.removeAttribute("message");
        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String keyword = request.getParameter("keyword");
        String searchedCategory = request.getParameter("searchedCategory");
        String filter = request.getParameter("filter");

        // renvoi si user est connecté
        boolean isConnected = BaseController.isConnected(request.getSession().getAttribute("idUser"));
        HttpSession session = request.getSession();

        try {
            if (!isConnected) {
                request.setAttribute("itemsList", ManagerFactory.getItemManager().getNonConnectedList(keyword, searchedCategory));
            } else {
                if (filter == null) {
                    request.setAttribute("itemsList", ManagerFactory.getItemManager().getConnectedList(keyword, searchedCategory));
                } else {
                    int idUser = (int) request.getSession().getAttribute("idUser");
                    request.setAttribute("itemsList", ManagerFactory.getItemManager().searchedItemsByFilter(filter, idUser));
                }
            }
            request.setAttribute("categoriesList", BaseController.getCategoriesList());
        } catch (BusinessException | SQLException businessException) {
            businessException.printStackTrace();
        }
        request.setAttribute("message", request.getSession().getAttribute("message"));
        session.removeAttribute("message");
        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }
}

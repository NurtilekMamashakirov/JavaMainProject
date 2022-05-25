package controllers;

import constants.Constants;
import database.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "DisciplinesDeleteController", urlPatterns = "/discipline-delete")
public class DisciplinesDeleteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = Constants.context.getBean("DBManager", DBManager.class);
        String idsToDelete = req.getParameter("idsToDelete");
        String[] ids = idsToDelete.split(" ");
        for (String id : ids) {
            manager.deleteDisciplineById(id);
        }
        resp.sendRedirect("/disciplines");
    }
}

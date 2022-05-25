package controllers;

import constants.Constants;
import database.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "DisciplinesCreateController", urlPatterns = "/discipline-create")
public class DisciplinesCreateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/discipline-create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = Constants.context.getBean("DBManager", DBManager.class);
        String disciplineName = req.getParameter("disciplineName");
        if (disciplineName.isEmpty()) {
            req.setAttribute("error", 1);
            req.getRequestDispatcher("jsp/discipline-create.jsp").forward(req, resp);
            return;
        }
        manager.createDiscipline(disciplineName);
        resp.sendRedirect("/disciplines");
    }
}

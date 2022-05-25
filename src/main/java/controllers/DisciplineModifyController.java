package controllers;

import constants.Constants;
import database.DBManager;
import entity.Discipline;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "DisciplineModifyController", urlPatterns = "/discipline-modify")
public class DisciplineModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = Constants.context.getBean("DBManager", DBManager.class);
        String idToModify = req.getParameter("idToModify");
        Discipline discipline = manager.getDisciplineById(idToModify);
        req.setAttribute("discipline", discipline);
        req.getRequestDispatcher("jsp/discipline-modify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = Constants.context.getBean("DBManager", DBManager.class);
        String disciplineName = req.getParameter("disciplineName");
        String idDiscipline = req.getParameter("idDiscipline");
        if (disciplineName.isEmpty()) {
            req.setAttribute("error", 1);
            req.getRequestDispatcher("jsp/discipline-modify.jsp").forward(req,resp);
            return;
        }
        manager.modifyDiscipline(idDiscipline, disciplineName);
        resp.sendRedirect("/disciplines");
    }
}

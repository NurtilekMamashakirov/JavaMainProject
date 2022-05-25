package controllers;

import database.DBManager;
import entity.Discipline;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static constants.Constants.context;

@WebServlet(name = "TermsCreateController", urlPatterns = "/term-create")
public class TermsCreateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = context.getBean("DBManager", DBManager.class);
        List<Discipline> disciplines = manager.getAllActiveDisciplines();
        req.setAttribute("disciplines", disciplines);

        req.getRequestDispatcher("jsp/term-create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = context.getBean("DBManager", DBManager.class);

        String ids[] = req.getParameterValues("idsDisciplines");
        String duration = req.getParameter("duration");

        String lastName = manager.getLastTermName();
        String newName = "Семестр " + (Integer.parseInt(lastName.split(" ")[1]) + 1);

        manager.createNewTerm(newName, duration, ids);
        resp.sendRedirect("/terms");
    }
}

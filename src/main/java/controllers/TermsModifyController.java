package controllers;

import database.DBManager;
import entity.Discipline;
import entity.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static constants.Constants.context;

@WebServlet (name = "TermsModifyController", urlPatterns = "/term-modify")
public class TermsModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = context.getBean("DBManager", DBManager.class);
        String idSelectedTerm = req.getParameter("idTermToModify");
        Term term = manager.getTermById(idSelectedTerm);
        List<Discipline> disciplines = manager.getAllActiveDisciplines();
        List<Discipline> disciplinesByTerm = manager.getDisciplinesByTerm(idSelectedTerm);
        req.setAttribute("term", term);
        req.setAttribute("disciplines", disciplines);
        req.setAttribute("disciplinesByTerm", disciplinesByTerm);
        req.getRequestDispatcher("jsp/term-modify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = context.getBean("DBManager", DBManager.class);
        String idTermModify = req.getParameter("idTermModify");
        String duration = req.getParameter("duration");
        String[] idsDisciplines = req.getParameterValues("idsDisciplines");
        manager.modifyTerm(idTermModify, duration, idsDisciplines);
        resp.sendRedirect("/terms");
    }
}

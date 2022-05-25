package controllers;

import constants.Constants;
import database.DBManager;
import entity.Discipline;
import entity.Term;
import services.TermService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static constants.Constants.context;

@WebServlet(name = "TermsController", urlPatterns = "/terms")
public class TermsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = context.getBean("DBManager", DBManager.class);
        List<Term> terms = manager.getAllActiveTerms();
        req.setAttribute("terms", terms);
        String idSelectedTerm = req.getParameter("idSelectedTerm");
        Term selectedTerm = null;
        if (idSelectedTerm == null) {
            selectedTerm = terms.get(0);
            idSelectedTerm = String.valueOf(terms.get(0).getId());
            req.setAttribute("selectedTerm", selectedTerm);
        } else {
            selectedTerm = TermService.getTermById(terms, idSelectedTerm);
            req.setAttribute("selectedTerm", selectedTerm);
        }

        List<Discipline> disciplines = manager.getDisciplinesByTerm(idSelectedTerm);
        req.setAttribute("disciplines", disciplines);


        req.getRequestDispatcher("jsp/terms.jsp").forward(req, resp);
    }
}

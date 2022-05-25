package controllers;

import constants.Constants;
import database.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.Constants.context;

@WebServlet (name = "TermsDeleteController", urlPatterns = "/term-delete")
public class TermsDeleteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idSelectedTerm = req.getParameter("idTermToDelete");
        DBManager manager = context.getBean("DBManager", DBManager.class);
        manager.deleteTerm(idSelectedTerm);
        resp.sendRedirect("/terms");
    }
}

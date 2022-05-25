package controllers;

import database.DBManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.Constants.context;

@WebServlet (name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String roleId = req.getParameter("role");

        DBManager manager = context.getBean("DBManager", DBManager.class);

        if (manager.canLogin(login, password, roleId)) {
            req.getSession().setAttribute("role", roleId);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("isLogin", 1);
            resp.sendRedirect("/");
        } else {
            req.setAttribute("message", 1);
            req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
        }
    }
}

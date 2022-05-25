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

@WebServlet(name = "StudentsCreateController", urlPatterns = "/student-create")
public class StudentsCreateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/student-create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String group = req.getParameter("group");
        String dateFromUser = req.getParameter("date");

        if (surname.isEmpty() || name.isEmpty() || group.isEmpty() || dateFromUser.isEmpty()) {
            req.setAttribute("error", "1");
            req.getRequestDispatcher("jsp/student-create.jsp").forward(req, resp);
            return;
        }

        String dateToShow =
                String.valueOf(dateFromUser.charAt(6))
                        + dateFromUser.charAt(7)
                        + dateFromUser.charAt(8) + dateFromUser.charAt(9) + "-"
                        + dateFromUser.charAt(0) + dateFromUser.charAt(1) + "-"
                        + dateFromUser.charAt(3) + dateFromUser.charAt(4);

        DBManager manager = context.getBean("DBManager", DBManager.class);
        manager.createStudent(surname, name, group, dateToShow);
        resp.sendRedirect("/students");


    }
}

package controllers;

import database.DBManager;
import entity.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.Constants.context;

@WebServlet(name = "StudentsModifyController", urlPatterns = "/student-modify")
public class StudentsModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("modifyHidden");
        DBManager dbManager = context.getBean("DBManager", DBManager.class);
        Student student = dbManager.getStudentById(id);
        req.setAttribute("student", student);
        req.getRequestDispatcher("jsp/student-modify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String group = req.getParameter("group");
        String dateFromUser = req.getParameter("date");

        if (surname.isEmpty() || name.isEmpty() || group.isEmpty() || dateFromUser.isEmpty()) {
            req.setAttribute("error", "1");
            req.getRequestDispatcher("jsp/student-modify.jsp").forward(req, resp);
            return;
        }

        String dateToShow = String.valueOf(dateFromUser.charAt(6)) + String.valueOf(dateFromUser.charAt(7)) + String.valueOf(dateFromUser.charAt(8)) + String.valueOf(dateFromUser.charAt(9)) + "-" + String.valueOf(dateFromUser.charAt(0)) + String.valueOf(dateFromUser.charAt(1)) + "-" + String.valueOf(dateFromUser.charAt(3)) + String.valueOf(dateFromUser.charAt(4));

        DBManager manager = context.getBean("DBManager", DBManager.class);
        manager.modifyStudent(id, surname, name, group, dateToShow);
        resp.sendRedirect("/students");
    }
}

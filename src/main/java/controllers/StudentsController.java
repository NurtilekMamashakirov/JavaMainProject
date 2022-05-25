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
import java.util.List;

import static constants.Constants.context;

@WebServlet (name = "StudentsController", urlPatterns = "/students")
public class StudentsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = context.getBean("DBManager", DBManager.class);
        List<Student> students = manager.getAllActiveStudents();
        req.setAttribute("students", students);
        req.getRequestDispatcher("jsp/students.jsp").forward(req, resp);
    }
}

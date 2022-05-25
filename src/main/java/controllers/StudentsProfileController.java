package controllers;

import database.DBManager;
import entity.Discipline;
import entity.Student;
import entity.Term;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.MarkServices;
import services.TermService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.Constants.context;

@WebServlet(name = "StudentsProfileController", urlPatterns = "/student-profile")
public class StudentsProfileController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("profileHidden");
        String idSelectedTerm = req.getParameter("idSelectedTerm");

        DBManager manager = context.getBean("DBManager", DBManager.class);

        Student student = manager.getStudentById(studentId);
        req.setAttribute("student", student);

        List<Term> terms = manager.getAllActiveTerms();
        Term selectedTerm = null;
        req.setAttribute("terms", terms);
        if (idSelectedTerm == null) {
            selectedTerm = terms.get(0);
            req.setAttribute("selectedTerm", selectedTerm);
        } else {
            selectedTerm = TermService.getTermById(terms, idSelectedTerm);
            req.setAttribute("selectedTerm", selectedTerm);
        }


        ArrayList<ArrayList> disciplinesAndMarks = manager.getDisciplinesAndMarksByTermAndStudent(selectedTerm.getId(), studentId);
//        Map<Discipline, Integer> disciplinesAndMarks = manager.getDisciplinesAndMarksByTermAndStudent(selectedTerm.getId(), studentId);
        if (!MarkServices.checkForMarks(disciplinesAndMarks)) {
//            List<Discipline> disciplines = manager.getDisciplinesByTerm(selectedTerm.getId());
            req.setAttribute("disciplinesAndMarks", disciplinesAndMarks);
            req.setAttribute("haveMarks", 0);
        } else {
            double average = MarkServices.getAverageMark(disciplinesAndMarks);
            req.setAttribute("average", average);
            req.setAttribute("disciplinesAndMarks", disciplinesAndMarks);
            req.setAttribute("haveMarks", 1);
        }


        req.getRequestDispatcher("jsp/student-profile.jsp").forward(req, resp);
    }
}

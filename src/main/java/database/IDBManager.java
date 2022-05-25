package database;

import entity.Discipline;
import entity.Student;
import entity.Term;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IDBManager {

    List<Student> getAllActiveStudents();

    void createStudent(String surname, String name, String group, String date);

    void deleteStudent(String id);

    Student getStudentById(String id);

    void modifyStudent(String id, String surname, String name, String group, String dateToShow);

    List<Term> getAllActiveTerms();

    List<Discipline> getDisciplinesByTerm(String id);

    ArrayList<ArrayList> getDisciplinesAndMarksByTermAndStudent(int termId, String studentId);

    boolean canLogin (String login, String password, String role);

    ArrayList<Discipline> getAllActiveDisciplines();


    String getLastTermName();

    void createNewTerm(String newName, String duration, String[] ids);

    void deleteTerm(String idSelectedTerm);

    Term getTermById(String idSelectedTerm);

    void modifyTerm(String idTermModify, String duration, String[] idsDisciplines);

    void createDiscipline(String disciplineName);

    void deleteDisciplineById(String id);

    Discipline getDisciplineById(String idToModify);

    void modifyDiscipline(String idDiscipline, String disciplineName);
}

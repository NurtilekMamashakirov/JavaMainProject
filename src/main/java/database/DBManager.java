package database;

import constants.Constants;
import entity.Discipline;
import entity.Student;
import entity.Term;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBManager implements IDBManager {

    @Override
    public List<Student> getAllActiveStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from student where status = 1");
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setSurname(rs.getString("surname"));
                student.setName(rs.getString("name"));
                student.setGroup(rs.getString("group"));
                student.setDate(rs.getDate("date"));
                student.setStatus(1);
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
//        List<Student> students = new ArrayList<>();
//        SessionFactory sessionFactory = new Configuration()
//                .configure("hibernate.hbm.xml")
//                .addAnnotatedClass(Student.class)
//                .buildSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        students = session.createQuery("from Student where status = 1").getResultList();
//        session.getTransaction().commit();
//        return students;
    }

    @Override
    public void createStudent(String surname, String name, String group, String date) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `student` (`surname`, `name`, `group`, `date`) VALUES ('" + surname + "', '" + name + "', '" + group + "', '" + date + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        SessionFactory sessionFactory = new Configuration()
//                .configure("hibernate.hbm.xml")
//                .addAnnotatedClass(Student.class)
//                .buildSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//         Student student = new Student();
//         student.setSurname(surname);
//         student.setName(name);
//         student.setGroup(group);
//         student.setDate(Date.valueOf(date));
//         session.save(student);
    }

    @Override
    public void deleteStudent(String id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `student` SET `status` = '0' WHERE (`id` = '" + id + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentById(String id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from student where status = 1 and id = " + id);
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setSurname(rs.getString("surname"));
                student.setName(rs.getString("name"));
                student.setGroup(rs.getString("group"));
                student.setDate(rs.getDate("date"));
                student.setStatus(1);
                return student;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void modifyStudent(String id, String surname, String name, String group, String dateToShow) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `students_29`.`student` SET `surname` = '" + surname + "', `name` = '" + surname + "', `group` = '" + group + "', `date` = '" + dateToShow + "' WHERE (`id` = '" + id + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Term> getAllActiveTerms() {
        ArrayList<Term> terms = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from term where status = 1");
            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getInt("id"));
                term.setName(rs.getString("name"));
                term.setDuration(rs.getString("duration"));
                term.setStatus(1);
                terms.add(term);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return terms;
    }

    @Override
    public List<Discipline> getDisciplinesByTerm(String termId) {
        List<Discipline> disciplines = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT d.id, d.discipline FROM term_discipline as td \n" +
                    "left join discipline as d on td.id_discipline = d.id\n" +
                    "where td.id_term = " + termId + " and d.status = 1 and td.status = '1'");
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("discipline"));
                discipline.setStatus(1);
                disciplines.add(discipline);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    @Override
    public ArrayList<ArrayList> getDisciplinesAndMarksByTermAndStudent(int termId, String studentId) {
        ArrayList<ArrayList> disciplinesAndMarks = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT d.id, d.discipline, m.mark FROM term_discipline as td\n" +
                    "left join discipline as d on td.id_discipline = d.id\n" +
                    "left join mark as m on td.id = m.id_term_discipline\n" +
                    "left join student as s on m.id_student = s.id\n" +
                    "where td.id_term = " + termId + " and s.id = " + studentId + " and d.status = 1 and s.status = 1");
            while (rs.next()) {
                ArrayList disciplineAndMark = new ArrayList();
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("discipline"));
                discipline.setStatus(1);
                Integer mark = rs.getInt("mark");
                disciplineAndMark.add(discipline);
                disciplineAndMark.add(mark);
                disciplinesAndMarks.add(disciplineAndMark);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disciplinesAndMarks;
    }

    @Override
    public boolean canLogin(String login, String password, String roleId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students_29.user_role as ur\n" +
                    "left join user as us on ur.id_user = us.id\n" +
                    "where us.login = '" + login + "' and us.password = '" + password + "' and id_role = " + roleId);
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Discipline> getAllActiveDisciplines() {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM discipline where status = 1");
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("discipline"));
                discipline.setStatus(1);
                disciplines.add(discipline);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    @Override
    public String getLastTermName() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM term order by id desc limit 1;");
            while (rs.next()) {
                return rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createNewTerm(String newName, String duration, String[] ids) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `students_29`.`term` (`name`, `duration`) VALUES ('" + newName + "', '" + duration + "');");

            ResultSet rs = stmt.executeQuery("SELECT last_insert_id() as id");
            int idTerm = 0;
            while (rs.next()) {
                idTerm = rs.getInt("id");
            }
            for (String idDiscipline : ids) {
                stmt.execute("INSERT INTO `students_29`.`term_discipline` (`id_term`, `id_discipline`) VALUES ('" + idTerm + "', '" + idDiscipline + "');");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTerm(String idSelectedTerm) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `term` SET `status` = '0' WHERE (`id` = '" + idSelectedTerm + "');");
            stmt.execute("UPDATE `term_discipline` set `status` = '0' where (`id_term` = " + idSelectedTerm + ");");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Term getTermById(String idSelectedTerm) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from `term` where status = 1 and id = " + idSelectedTerm);
            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getInt("id"));
                term.setName((rs.getString("name")));
                term.setDuration(rs.getString("duration"));
                term.setStatus(1);
                return term;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void modifyTerm(String idTermModify, String duration, String[] idsDisciplines) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `term` SET `duration` = '" + duration + "' WHERE (`id` = '" + idTermModify + "');");
            stmt.execute("UPDATE `term_discipline` set `status` = '0' where (`id_term` = " + idTermModify + ");");
            for (String idsDiscipline : idsDisciplines) {
                stmt.execute("INSERT INTO `students_29`.`term_discipline` (`id_term`, `id_discipline`) VALUES ('" + idTermModify + "', '" + idsDiscipline + "');");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createDiscipline(String disciplineName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `students_29`.`discipline` (`discipline`) VALUES ('" + disciplineName + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDisciplineById(String id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `discipline` SET `status` = '0' WHERE (`id` = '" + id + "');");
            stmt.execute("UPDATE `term_discipline` set `status` = '0' where (`id_discipline` = " + id + ");");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Discipline getDisciplineById(String idToModify) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from `discipline` where status = 1 and id = " + idToModify);
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline((rs.getString("discipline")));
                discipline.setStatus(1);
                return discipline;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void modifyDiscipline(String idDiscipline, String disciplineName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `students_29`.`discipline` SET `discipline` = '" + disciplineName + "' WHERE (`id` = '" + idDiscipline + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

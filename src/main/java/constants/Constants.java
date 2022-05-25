package constants;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public interface Constants {
    String DB_URL_CONNECTION = "jdbc:mysql://localhost:3306/students_29?user=root&password=M1211217n";
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
}

package ro.uaic.info.lab3.repositories;

import ro.uaic.info.lab3.entites.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ApplicationScoped
public class StudentRepository {
    private static final String INSERT_STATEMENT = "INSERT INTO STUDENTS (name, exam_name) VALUES(?, ?);";
    private static final String GET_ALL_STATEMENT = "SELECT name, exam_name FROM STUDENTS;";

    @Inject
    private Connection connection;

    public void save(Student student) throws SQLException {
        final  String studentName = student.getName();
        for(String examName: student.getExamNames()) {
            PreparedStatement stmt = connection.prepareStatement(INSERT_STATEMENT);
            stmt.setString(1, studentName);
            stmt.setString(2, examName);
            stmt.executeUpdate();
            stmt.close();
        }
    }
}

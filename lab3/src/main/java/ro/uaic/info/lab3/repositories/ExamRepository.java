package ro.uaic.info.lab3.repositories;

import ro.uaic.info.lab3.entites.Exam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ExamRepository {
    private static final String INSERT_STATEMENT = "INSERT INTO EXAMS (name, start_date, end_date) VALUES(?, ?, ?);";
    private static final String GET_ALL_STATEMENT = "SELECT name, start_date, end_date FROM EXAMS;";
    @Inject
    private Connection connection;

    public void save(Exam exam) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(INSERT_STATEMENT);
        stmt.setString(1, exam.getName());
        stmt.setDate(2, new Date(exam.getStartDate().getTime()));
        stmt.setDate(3, new Date(exam.getEndDate().getTime()));
        stmt.executeUpdate();
        stmt.close();
    }

    public List<Exam> getAll() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(GET_ALL_STATEMENT);
        ResultSet resultSet = stmt.executeQuery();
        List<Exam> exams = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString(1);
            Date startDate = resultSet.getDate(2);
            Date endDate = resultSet.getDate(3);
            exams.add(new Exam(name, startDate, endDate));
        }
        stmt.close();
        return exams;
    }
}

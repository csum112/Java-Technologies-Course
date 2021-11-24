package ro.uaic.info.lab3.beans;

import lombok.Data;
import lombok.SneakyThrows;
import ro.uaic.info.lab3.entites.Exam;
import ro.uaic.info.lab3.entites.Student;
import ro.uaic.info.lab3.repositories.ExamRepository;
import ro.uaic.info.lab3.util.FormHandler;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Named
@RequestScoped
public class StudentBean {

    @Resource
    UserTransaction userTransaction;

    final private FormHandler<Student> studentFormHandler;
    final private ExamRepository examRepository;
    final private List<String> examNames;
    private String name;
    private List<String> selectedExamNames;

    @Inject
    public StudentBean(ExamRepository examRepository, FormHandler<Student> formHandler) throws SQLException {
        this.examRepository = examRepository;
        this.studentFormHandler = formHandler;
        this.examNames = examRepository.findAll()
                .stream()
                .map(Exam::getName)
                .collect(Collectors.toList());
    }

    @Transactional
    public void submit() {
        final List<Exam> selectedExams = selectedExamNames.stream()
                .map(examRepository::findById)
                .filter(Optional::isEmpty)
                .map(Optional::get)
                .collect(Collectors.toList());
        this.studentFormHandler.handle(new Student(name, selectedExams));
    }
}

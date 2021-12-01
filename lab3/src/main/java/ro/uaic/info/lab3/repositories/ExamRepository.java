package ro.uaic.info.lab3.repositories;

import ro.uaic.info.lab3.entites.Exam;
import ro.uaic.info.lab3.entites.Exam_;
import ro.uaic.info.lab3.entites.Student;
import ro.uaic.info.lab3.entites.Student_;
import ro.uaic.info.lab3.util.SearchCriteria;

import javax.ejb.Stateless;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ExamRepository extends Dao<Exam> {
    public List<Exam> searchExams(SearchCriteria searchCriteria) {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<Exam> criteriaQuery = criteriaBuilder.createQuery(Exam.class);
        final Root<Student> studentRoot = criteriaQuery.from(Student.class);
        final Join<Student, Exam> join = studentRoot.join(Student_.exams);

        final List<Predicate> predicates = new ArrayList<>();
        if (searchCriteria.getExamName() != null)
            predicates.add(criteriaBuilder.equal(join.get(Exam_.NAME), searchCriteria.getExamName()));
        if (searchCriteria.getStartTime() != null)
            predicates.add(criteriaBuilder.equal(join.get(Exam_.START), searchCriteria.getStartTime()));
        if (searchCriteria.getStudentName() != null)
            predicates.add(criteriaBuilder.equal(studentRoot.get(Student_.name), searchCriteria.getStudentName()));

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        criteriaQuery.select(join);
        return em.createQuery(criteriaQuery).getResultList();
    }

}

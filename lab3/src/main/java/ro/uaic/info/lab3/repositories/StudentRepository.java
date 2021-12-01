package ro.uaic.info.lab3.repositories;

import ro.uaic.info.lab3.entites.Student;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;

@Stateless
public class StudentRepository extends Dao<Student> {

}

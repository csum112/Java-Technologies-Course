package ro.uaic.info.lab3.services;

import ro.uaic.info.lab3.entites.ExamWithResources;
import ro.uaic.info.lab3.entites.Resource;
import ro.uaic.info.lab3.repositories.ExamWithResourcesRepository;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Stateful
public class ResourceAssignmentService {
    @EJB
    private ExamWithResourcesRepository examWithResourcesRepository;

    @Transactional
    public void assignResources(ExamWithResources exam, Collection<Resource> resources) {
       exam.getResources().addAll(resources);
       examWithResourcesRepository.update(exam);
    }
}

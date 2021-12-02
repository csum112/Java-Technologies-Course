package ro.uaic.info.lab3.services;

import ro.uaic.info.lab3.entites.ExamWithResources;
import ro.uaic.info.lab3.entites.Resource;
import ro.uaic.info.lab3.repositories.ExamWithResourcesRepository;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.transaction.Transactional;
import java.util.Collection;

@Stateful
public class ResourceAssignmentService {
    @EJB
    private ExamWithResourcesRepository examWithResourcesRepository;

    @EJB
    private ResourceService resourceService;

    @EJB
    private ResourceTracker resourceTracker;

    @Transactional
    public void assignResources(ExamWithResources exam, Collection<Resource> resources) {
        resources.forEach(this::ensureResourceIsFree);
        exam.getResources().addAll(resources);
        examWithResourcesRepository.update(exam);
        resources.forEach(resource -> resourceTracker.addAssignment(exam, resource));
    }

    private void ensureResourceIsFree(Resource resource) {
        if (!resourceService.isAvailable(resource)) {
            throw new IllegalStateException("Cannot add an unavailable resource");
        }
    }
}

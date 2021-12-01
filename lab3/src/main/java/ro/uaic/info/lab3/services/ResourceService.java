package ro.uaic.info.lab3.services;

import ro.uaic.info.lab3.entites.Resource;
import ro.uaic.info.lab3.repositories.ExamWithResourcesRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ResourceService {
    @EJB
    private ExamWithResourcesRepository examWithResourcesRepository;

    public boolean isAvailable(Resource resource) {
        return examWithResourcesRepository.findByResource(resource).size() < resource.getCount();
    }

}

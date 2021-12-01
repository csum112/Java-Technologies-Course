package ro.uaic.info.lab3.repositories;

import ro.uaic.info.lab3.entites.ExamWithResources;
import ro.uaic.info.lab3.entites.Resource;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ExamWithResourcesRepository extends Dao<ExamWithResources> {
    public List<ExamWithResources> findByResource(Resource resource) {
        return em.createQuery("SELECT DISTINCT e FROM ExamWithResources e where :resource MEMBER OF e.resources", ExamWithResources.class)
                .setParameter("resource", resource)
                .getResultList();
    }
}

package ro.uaic.info.lab3.services;

import lombok.extern.slf4j.Slf4j;
import ro.uaic.info.lab3.entites.ExamWithResources;
import ro.uaic.info.lab3.entites.Resource;
import ro.uaic.info.lab3.repositories.ExamWithResourcesRepository;
import ro.uaic.info.lab3.util.TimeItInterceptor;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.interceptor.Interceptor;
import javax.interceptor.Interceptors;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Interceptors(TimeItInterceptor.class)
@Singleton
public class ResourceTracker {
    @EJB
    private ExamWithResourcesRepository examWithResourcesRepository;
    private final Map<Resource, List<ExamWithResources>> map = new HashMap<>();

    /**
     * Re-initialize the map every hour
     */
    @Schedule(hour = "*")
    @PostConstruct
    private void init() {
        map.clear();
        examWithResourcesRepository
                .findAll()
                .forEach(exam -> {
                    exam.getResources().forEach(resource -> {
                        ensureResourceListExists(resource);
                        map.get(resource).add(exam);
                    });
                });
    }


    public void removeAssignment(ExamWithResources exam, Resource resource) {
        ensureAssignmentExists(exam, resource);
        map.get(resource).remove(exam);
    }

    public void addAssignment(ExamWithResources exam, Resource resource) {
        ensureResourceListExists(resource);
        map.get(resource).add(exam);
    }

    private void ensureResourceListExists(Resource resource) {
        map.computeIfAbsent(resource, k -> new ArrayList<>());
    }

    private void ensureAssignmentExists(ExamWithResources exam, Resource resource) {
        ensureResourceListExists(resource);
        if (this.map.get(resource).contains(exam)) {
            throw new IllegalStateException();
        }
    }
}

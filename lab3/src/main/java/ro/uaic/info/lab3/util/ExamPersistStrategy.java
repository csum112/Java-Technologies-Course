package ro.uaic.info.lab3.util;


import ro.uaic.info.lab3.entites.Exam;
import ro.uaic.info.lab3.entites.ExamWithBibliography;
import ro.uaic.info.lab3.entites.ExamWithResources;
import ro.uaic.info.lab3.repositories.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ExamPersistStrategy {
    @Inject
    private ExamRepository examRepository;
    @Inject
    private ExamWithResourcesRepository examWithResourcesRepository;
    @Inject
    private ExamWithBibliographyRepository examWithBibliographyRepository;
    @Inject
    private ResourceRepository resourceRepository;
    @Inject
    private BibliographyRepository bibliographyRepository;

    public <E extends Exam> void save(E exam) {
        if (exam instanceof ExamWithResources) {
            final ExamWithResources examWithResources = (ExamWithResources) exam;
            examWithResources.getResources().forEach(resourceRepository::save);
            examWithResourcesRepository.save(examWithResources);
        } else if (exam instanceof ExamWithBibliography) {
            final ExamWithBibliography examWithBibliography = (ExamWithBibliography) exam;
            examWithBibliography.getBibliographyItems().forEach(bibliographyRepository::save);
            examWithBibliographyRepository.save((ExamWithBibliography) exam);
        } else {
            examRepository.save(exam);
        }
    }
}

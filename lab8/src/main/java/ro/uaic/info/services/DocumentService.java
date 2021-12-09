package ro.uaic.info.services;

import lombok.RequiredArgsConstructor;
import ro.uaic.info.dto.DocumentDTO;
import ro.uaic.info.entities.Document;
import ro.uaic.info.entities.User;
import ro.uaic.info.repositories.DocumentRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final UserService userService;

    @Transactional
    public void upload(@NotNull Long userID, @NotNull DocumentDTO doc) {
        final User user = userService.getOrCreate(userID);
        final Document document = new Document();
        document.setUser(user);
        document.setName(doc.getFileName());
        document.setData(document.getData());
        documentRepository.save(document);
    }

    @Transactional
    public void update(@NotNull Long userID, @NotNull Long documentID, DocumentDTO doc) throws IllegalAccessException {
        final Document document = documentRepository.findById(documentID)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Document with id %d not found", documentID)));
        userService.ensureUserExists(userID);
        ensureDocumentBelongsToUser(document, userService.getOrCreate(userID));
        document.setName(doc.getFileName());
        document.setData(doc.getData());
        documentRepository.update(document);
    }

    @Transactional
    public void remove(@NotNull Long userID, @NotNull Long documentID) throws IllegalAccessException {
        final Document document = documentRepository.findById(documentID)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Document with id %d not found", documentID)));
        userService.ensureUserExists(userID);
        ensureDocumentBelongsToUser(document, userService.getOrCreate(userID));
        documentRepository.remove(document);
    }

    @Transactional
    public List<DocumentDTO> getAll(Long userID) {
        userService.ensureUserExists(userID);
        return userService.getOrCreate(userID)
                .getFiles()
                .stream()
                .map(DocumentDTO::of)
                .collect(Collectors.toList());
    }

    private void ensureDocumentBelongsToUser(Document document, User user) throws IllegalAccessException {
        if (!Objects.equals(document.getUser().getId(), user.getId())) {
            throw new IllegalAccessException();
        }
    }
}

package ro.uaic.info.services;

import lombok.RequiredArgsConstructor;
import ro.uaic.info.daos.RecordDAO;
import ro.uaic.info.entities.Record;
import ro.uaic.info.entities.RecordCategories;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RecordsService {
    private final RecordDAO recordDAO;

    @Inject
    public RecordsService(RecordDAO recordDAO) {
        this.recordDAO = recordDAO;
    }

    public void handlePost(HttpServletRequest req, HttpServletResponse resp) {
        persistRecord(req);
        final List<Record> result = recordDAO.getAll();
        req.setAttribute("records", result);
    }

    private void persistRecord(HttpServletRequest req) {
        final RecordCategories category = RecordCategories.valueOf(
                req.getParameter("category").toUpperCase()
        );
        final String word = req.getParameter("key");
        final String definition = req.getParameter("value");

        final Record newRecord = new Record(category, word, definition);
        recordDAO.persist(newRecord);
    }

}

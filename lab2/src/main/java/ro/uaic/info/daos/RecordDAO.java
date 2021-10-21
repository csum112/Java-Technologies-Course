package ro.uaic.info.daos;

import ro.uaic.info.entities.Record;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class RecordDAO {
    private final List<Record> records = new ArrayList<>();

    public void persist(Record record)
    {
        this.records.add(record);
    }

    public List<Record> getAll() {
        return this.records;
    }
}

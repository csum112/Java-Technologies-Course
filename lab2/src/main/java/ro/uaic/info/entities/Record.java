package ro.uaic.info.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public final class Record {
    private final RecordCategories category;
    private final String word;
    private final String definition;
}

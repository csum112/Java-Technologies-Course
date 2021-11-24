package ro.uaic.info.lab3.util;

import lombok.Builder;
import lombok.Data;

import java.sql.Time;

@Data
@Builder
public class SearchCriteria {
    private final String studentName;
    private final String examName;
    private final Time startTime;
}

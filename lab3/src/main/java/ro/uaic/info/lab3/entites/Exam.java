package ro.uaic.info.lab3.entites;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Exam implements Serializable {
    private final String name;
    private final Date startDate;
    private final Date endDate;
}

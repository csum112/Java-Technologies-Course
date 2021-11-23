package ro.uaic.info.lab3.entites;

import lombok.Data;

import java.io.Serializable;
import java.sql.Time;
import java.time.Duration;
import java.util.Date;

@Data
public class Exam implements Serializable {
    private final String name;
    private final Time start;
    private final Long duration;
}

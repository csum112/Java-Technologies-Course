package ro.uaic.info.lab3.entites;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Student implements Serializable {
    private final String name;
    private final List<String> examNames;
}

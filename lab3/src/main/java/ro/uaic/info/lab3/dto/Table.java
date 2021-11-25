package ro.uaic.info.lab3.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Table {
    private final List<String> headers;
    private final List<Map<String, String>> items;
}

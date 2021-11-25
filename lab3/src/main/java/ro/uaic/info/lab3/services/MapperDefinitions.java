package ro.uaic.info.lab3.services;

import ro.uaic.info.lab3.dto.Table;
import ro.uaic.info.lab3.entites.Exam;
import ro.uaic.info.lab3.util.TableDataMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class MapperDefinitions {
    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;

    @Produces
    TableDataMapper<Exam> createExamMapper() {
        return exams -> {
            final var maps = exams.stream().map(this::getExamMap).collect(Collectors.toList());
            final var headers = List.of("Name", "Start Time", "Duration");
            return new Table(headers, maps);
        };
    }

    private Map<String, String> getExamMap(Exam exam) {
        final Map<String, String> map = new HashMap<>();
        map.put("Name", exam.getName());
        map.put("Start Time", exam.getStart().toLocalTime().format(dateTimeFormatter));
        map.put("Duration", exam.getDuration().toString());
        return map;
    }
}

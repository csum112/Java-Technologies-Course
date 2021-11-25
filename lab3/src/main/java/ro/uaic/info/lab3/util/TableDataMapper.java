package ro.uaic.info.lab3.util;

import ro.uaic.info.lab3.dto.Table;

import java.util.List;

public interface TableDataMapper<E> {
    Table map(List<E> item);
}

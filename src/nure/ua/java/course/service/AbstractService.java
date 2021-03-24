package nure.ua.java.course.service;

import nure.ua.java.course.presistence.entity.AbstractEntity;

import java.sql.SQLException;
import java.util.List;

public interface AbstractService <E extends AbstractEntity> {

    void create(E e) throws SQLException;
    void update(E e) throws SQLException;
    List<E> get(String s) throws SQLException;
}

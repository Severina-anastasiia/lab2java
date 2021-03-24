package nure.ua.java.course.presistence.repository;

import nure.ua.java.course.presistence.entity.AbstractEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AbstractRepository<E extends AbstractEntity> {

    void create(Connection connection, E e) throws SQLException;
    void update(Connection connection, E e) throws SQLException;
    List<E> getElement(Connection connection, String string) throws SQLException;
}

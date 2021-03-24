package nure.ua.java.course.service;

import nure.ua.java.course.ConnectionFactory;
import nure.ua.java.course.presistence.entity.Type;
import nure.ua.java.course.presistence.repository.TypeRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TypeService implements AbstractService<Type> {

    private TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public void create(Type type) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            typeRepository.create(connection, type);
            connection.commit();
        } catch (SQLException e){
            connection.rollback();
            throw e;
        }
    }

    @Override
    public void update(Type type) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            typeRepository.update(connection, type);
            connection.commit();
        } catch (SQLException e){
            connection.rollback();
            throw e;
        }
    }

    @Override
    public List<Type> get(String s) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            List<Type> types = typeRepository.getElement(connection, s);
            connection.commit();
            return types;
        } catch (SQLException e){
            connection.rollback();
            throw e;
        }
    }
}

package nure.ua.java.course.service;

import nure.ua.java.course.ConnectionFactory;
import nure.ua.java.course.presistence.entity.Computer;
import nure.ua.java.course.presistence.repository.ComputerRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ComputerService implements AbstractService<Computer>{

    private ComputerRepository computerRepository;

    public ComputerService(ComputerRepository computerRepository){
        this.computerRepository = computerRepository;
    }

    @Override
    public void create(Computer computer) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            computerRepository.create(connection, computer);
            connection.commit();
        } catch (SQLException e){
            connection.rollback();
            throw e;
        }
    }

    @Override
    public void update(Computer computer) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            computerRepository.update(connection, computer);
            connection.commit();
        } catch (SQLException e){
            connection.rollback();
            throw e;
        }
    }

    @Override
    public List<Computer> get(String s) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            List<Computer> computers = computerRepository.getElement(connection, s);
            connection.commit();
            return computers;
        } catch (SQLException e){
            connection.rollback();
            throw e;
        }
    }
}

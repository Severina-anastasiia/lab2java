package nure.ua.java.course.presistence.repository;

import nure.ua.java.course.presistence.entity.Computer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComputerRepository implements AbstractRepository<Computer>{

    private static final String SQL_UPDATE_COMPUTER = "UPDATE computers SET name = ?, id_type = ? WHERE (id = ?)";
    private static final String SQL_INSERT_INTO_COMPUTER = "INSERT INTO computers(id, name, id_type) VALUES (?, ?, ?)";
    private static final String SQL_FIND_BY_TYPE = "SELECT * FROM computers " +
            "WHERE id_type IN (SELECT id FROM types WHERE name = ?)";

    public void create(Connection connection, Computer computer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_COMPUTER);
        setSqlInsertIntoComputer(computer, preparedStatement);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void update(Connection connection, Computer computer) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_COMPUTER);
        setSqlUpdateComputer(computer, preparedStatement);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public List<Computer> getElement(Connection connection, String string) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_TYPE);
        preparedStatement.setString(1, string);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Computer> computers = getComputers(resultSet);
        preparedStatement.close();
        return computers;
    }

    private void setSqlInsertIntoComputer(Computer computer, PreparedStatement preparedStatement) throws SQLException {
        int k = 1;
        preparedStatement.setInt(k++, computer.getId());
        preparedStatement.setString(k++, computer.getName());
        preparedStatement.setInt(k++, computer.getId_type());
    }

    private void setSqlUpdateComputer(Computer computer, PreparedStatement preparedStatement) throws SQLException {
        int k = 1;
        preparedStatement.setString(k++, computer.getName());
        preparedStatement.setInt(k++, computer.getId_type());
        preparedStatement.setInt(k++, computer.getId());
    }

    private List<Computer> getComputers(ResultSet resultSet) throws SQLException {
        List<Computer> computers = new ArrayList<>();
        while (resultSet.next()){
            computers.add(getComputer(resultSet));
        }
        return computers;
    }

    private Computer getComputer(ResultSet resultSet) throws SQLException {
        Computer computer = new Computer();
        computer.setId(resultSet.getInt("id"));
        computer.setName(resultSet.getString("name"));
        computer.setId_type(resultSet.getInt("id_type"));
        return computer;
    }
}

package nure.ua.java.course.presistence.repository;

import nure.ua.java.course.presistence.entity.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeRepository implements AbstractRepository<Type>{

    private static final String SQL_UPDATE_TYPE = "UPDATE types SET name = ? WHERE (id = ?)";
    private static final String SQL_INSERT_TYPE = "INSERT INTO types(id, name) VALUES (?,?)";
    private static final String SQL_FIND_BY_COMPUTERS = "SELECT * FROM types " +
            "WHERE id IN (SELECT id_type FROM computers WHERE name = ?)";

    public void create(Connection connection, Type type) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement(SQL_INSERT_TYPE, Statement.RETURN_GENERATED_KEYS);
        setSqlInsertType(type, preparedStatement);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void update(Connection connection, Type type) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement(SQL_UPDATE_TYPE);
        setSqlUpdateType(type, preparedStatement);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public List<Type> getElement(Connection connection, String string) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_COMPUTERS);
        preparedStatement.setString(1, string);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Type> types = getTypes(resultSet);
        preparedStatement.close();
        return types;
    }

    private void setSqlInsertType(Type type, PreparedStatement preparedStatement) throws SQLException{
        int k = 1;
        preparedStatement.setInt(k++, type.getId());
        preparedStatement.setString(k++, type.getName());
    }

    private void setSqlUpdateType(Type type, PreparedStatement preparedStatement) throws SQLException {
        int k = 1;
        preparedStatement.setString(k++, type.getName());
        preparedStatement.setInt(k++, type.getId());
    }

    private Type getType(ResultSet resultSet) throws SQLException {
        Type type = new Type();
        type.setId(resultSet.getInt("id"));
        type.setName(resultSet.getString("name"));
        return type;
    }

    private List<Type> getTypes(ResultSet resultSet) throws SQLException {
        List<Type> types = new ArrayList<>();
        while (resultSet.next()){
            types.add(getType(resultSet));
        }
        return types;
    }


}

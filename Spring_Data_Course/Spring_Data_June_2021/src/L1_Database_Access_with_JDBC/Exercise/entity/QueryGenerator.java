package L1_Database_Access_with_JDBC.Exercise.entity;

import L1_Database_Access_with_JDBC.Exercise.Main;
import L1_Database_Access_with_JDBC.Exercise.interfaces.IQueryGenerator;

import java.io.IOException;
import java.sql.*;
import java.util.Locale;

public class QueryGenerator implements IQueryGenerator {
    private Connection connection = Main.getConnection();
    private StringBuilder query = new StringBuilder();

    public QueryGenerator() throws IOException, SQLException {
    }

    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public int insertInto(String table, String columns, String values) throws SQLException {
        String statement = "INSERT IGNORE INTO $tableName($columns) VALUES ($values)";

        if (injectionCheck(columns)
                && injectionCheck(values)
                && injectionCheck(table)) {
            statement = statement.replace("$columns", columns);
            statement = statement.replace("$values", values);
            statement = statement.replace("$tableName", table);
        }

        this.query.append(statement);

        PreparedStatement preparedStatement = getConnection().prepareStatement(this.query.toString());

        this.query = new StringBuilder();

        return preparedStatement.executeUpdate();
    }

    @Override
    public int updateColumnToValueWithCondition(String tableName, String columnName, String value, String condition) throws SQLException {
        String statement = "UPDATE $tableName SET $columnName = $value WHERE $condition";

        if (injectionCheck(columnName)
                && injectionCheck(tableName)
                && injectionCheck(condition)
                && injectionCheck(value)) {
            statement = statement.replace("$columnName", columnName);
            statement = statement.replace("$tableName", tableName);
            statement = statement.replace("$value", value);
            statement = statement.replace("$condition", condition);
        }

        this.query.append(statement);

        PreparedStatement preparedStatement = getConnection().prepareStatement(this.query.toString());

        this.query = new StringBuilder();

        return preparedStatement.executeUpdate();
    }

    @Override
    public int updateColumnToUpperCase(String tableName, String columnName, String value, String condition) throws SQLException {
        String statement = "UPDATE $tableName SET $columnName = UPPER($columnName) WHERE $condition";

        if (injectionCheck(columnName)
                && injectionCheck(tableName)
                && injectionCheck(condition)) {
            statement = statement.replace("$columnName", columnName);
            statement = statement.replace("$tableName", tableName);
            statement = statement.replace("$condition", condition);
        }

        this.query.append(statement);

        PreparedStatement preparedStatement = getConnection().prepareStatement(this.query.toString());

        preparedStatement.setString(1, value);

        this.query = new StringBuilder();

        return preparedStatement.executeUpdate();
    }

    @Override
    public int callPreparedStatement(String statementName, String value) throws SQLException {
        String statement = "CALL $statementName($value)";

        if (injectionCheck(statementName) && injectionCheck(value)) {
            statement = statement.replace("$statementName", statementName);
            statement = statement.replace("$value", value);
        }

        this.query.append(statement);

        CallableStatement callableStatement = getConnection().prepareCall(this.query.toString());

        this.query = new StringBuilder();

        return callableStatement.executeUpdate();
    }

    @Override
    public int deleteById(String table, String idColumn, int id) throws SQLException {
        String statement = "DELETE FROM $table WHERE $idColumn = ?";

        if (injectionCheck(table) && injectionCheck(idColumn)) {
            statement = statement.replace("$table", table);
            statement = statement.replace("$idColumn", idColumn);
        }

        this.query.append(statement);

        PreparedStatement preparedStatement = getConnection().prepareStatement(this.query.toString());

        preparedStatement.setInt(1, id);


        this.query = new StringBuilder();

        return preparedStatement.executeUpdate();
    }

    @Override
    public ResultSet orderedSelectWithJoin(String columnNames, String tableName, String joinAndGrouping, String orderStatement) throws SQLException {
        String statement = "SELECT $columnName FROM $tableName AS v\n" +
                "$joinAndGrouping\n" +
                "$orderStatement";
        if (injectionCheck(columnNames)
                && injectionCheck(tableName)
                && injectionCheck(joinAndGrouping)
                && injectionCheck(orderStatement)) {
            statement = statement.replace("$columnName", columnNames);
            statement = statement.replace("$tableName", tableName);
            statement = statement.replace("$joinAndGrouping", joinAndGrouping);
            statement = statement.replace("$orderStatement", orderStatement);
        }

        this.query.append(statement);

        PreparedStatement preparedStatement = getConnection().prepareStatement(this.query.toString());

        this.query = new StringBuilder();

        return preparedStatement.executeQuery();
    }

    @Override
    public ResultSet selectByFilterCondition(String columns, String table, String filterCondition) throws SQLException {
        String statement = "SELECT $columnName FROM $tableName WHERE $filterCondition";
        if (injectionCheck(columns)
                && injectionCheck(table)
                && injectionCheck(filterCondition)) {
            statement = statement.replace("$columnName", columns);
            statement = statement.replace("$tableName", table);
            statement = statement.replace("$filterCondition", filterCondition);
        }

        this.query.append(statement);

        PreparedStatement preparedStatement = getConnection().prepareStatement(this.query.toString());

        this.query = new StringBuilder();

        return preparedStatement.executeQuery();
    }

    private boolean injectionCheck(String input) {
        input = input.toLowerCase(Locale.ROOT);
        return !(input.contains("union")
                || input.contains("update")
                || input.contains("delete")
                || input.contains("insert"));
    }
}
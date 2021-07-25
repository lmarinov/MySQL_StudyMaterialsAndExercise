package L1_Database_Access_with_JDBC.Exercise.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IQueryGenerator {
    int insertInto(String table, String columns, String values) throws SQLException;

    int updateColumnToValueWithCondition(String tableName, String columnName, String value, String condition) throws SQLException;

    int updateColumnToUpperCase(String tableName, String columnName, String value, String condition) throws SQLException;

    int callPreparedStatement(String statementName, String value) throws SQLException;

    int deleteById(String table, String idColumn, int id) throws SQLException;

    ResultSet orderedSelectWithJoin(String columnNames, String tableName, String joinAndGrouping, String orderStatement) throws SQLException;

    ResultSet selectByFilterCondition(String columns, String table, String filterCondition) throws SQLException;
}

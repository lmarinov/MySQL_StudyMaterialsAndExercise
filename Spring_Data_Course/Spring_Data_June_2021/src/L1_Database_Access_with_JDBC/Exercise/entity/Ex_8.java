package L1_Database_Access_with_JDBC.Exercise.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex_8 {

    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void execute() throws IOException, SQLException {
        QueryGenerator queryGenerator = new QueryGenerator();

        System.out.println("Please enter minion IDs separated by spaces, for which to increment age and change name:");

        String[] ids = bf.readLine().split("\s+");

        queryGenerator.updateColumnToValueWithCondition("minions", "`name`", "LOWER(`name`)", String.format("id IN (%s)", String.join(", ", ids)));
        queryGenerator.updateColumnToValueWithCondition("minions", "age", "age + 1", String.format("id IN (%s)", String.join(", ", ids)));

        ResultSet resultSet = queryGenerator.selectByFilterCondition("`name`, age", "minions", "id > 0");

        while (resultSet.next()){
            System.out.printf("%s %d%n", resultSet.getString("name"), resultSet.getInt("age"));
        }
    }
}

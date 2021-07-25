package L1_Database_Access_with_JDBC.Exercise.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex_9 {

    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void execute() throws IOException, SQLException {
        QueryGenerator queryGenerator = new QueryGenerator();

        System.out.println("Please input minion id:");

        int minionId = Integer.parseInt(bf.readLine());

        queryGenerator.callPreparedStatement("usp_get_older", String.format("%d", minionId));

        ResultSet resultSet = queryGenerator.selectByFilterCondition("`name`, age", "minions", String.format("id = %d", minionId));

        while (resultSet.next()){
            System.out.printf("%s %d", resultSet.getString("name"), resultSet.getInt("age"));
        }
    }
}

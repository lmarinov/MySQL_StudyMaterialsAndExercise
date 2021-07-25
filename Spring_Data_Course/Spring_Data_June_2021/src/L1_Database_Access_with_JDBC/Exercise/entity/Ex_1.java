package L1_Database_Access_with_JDBC.Exercise.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex_1 {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static void execute() throws IOException, SQLException {

        System.out.println("Enter max id:");
        int maxId = Integer.parseInt(bf.readLine());
        QueryGenerator queryGenerator = new QueryGenerator();

        ResultSet resultSet = queryGenerator.selectByFilterCondition("*", "minions", String.format("id < %d", maxId));

        while (resultSet.next()){
            System.out.printf("%s %d %n", resultSet.getString("name"), resultSet.getInt("age"));
        }
    }
}

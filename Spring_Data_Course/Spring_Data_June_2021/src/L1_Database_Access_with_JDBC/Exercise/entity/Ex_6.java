package L1_Database_Access_with_JDBC.Exercise.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex_6 {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void execute() throws IOException, SQLException {
        QueryGenerator queryGenerator = new QueryGenerator();

        System.out.println("Enter villain ID:");

        int villainId = Integer.parseInt(bf.readLine());

        ResultSet resultSet = queryGenerator.selectByFilterCondition("`name`", "villains", String.format("id = %d", villainId));

        String villainName = "";

        if (resultSet.next()){
            villainName = resultSet.getString("name");
        }else{
            System.out.println("No such villain was found");
            return;
        }

        resultSet.close();

        int numberOfMinions = queryGenerator.deleteById("minions_villains", "villain_id", villainId);

        queryGenerator.deleteById("villains", "id", villainId);

        System.out.printf("%s was deleted%n", villainName);
        System.out.printf("%d minions were released%n", numberOfMinions);
    }
}

package L1_Database_Access_with_JDBC.Exercise.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex_3 {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void execute() throws IOException, SQLException {

        QueryGenerator queryGenerator = new QueryGenerator();

        System.out.println("Please enter a villain id for which you want to find the minions: ");
        int villain_id = Integer.parseInt(bf.readLine());


        ResultSet resultSet = queryGenerator.selectByFilterCondition("v.name, m.name, m.age", "minions AS m JOIN minions_villains mv on m.id = mv.minion_id JOIN villains v on mv.villain_id = v.id", String.format("villain_id = %d", villain_id));


        if (!resultSet.isBeforeFirst()){
            System.out.printf("No villain with ID %d exists in the database.%n", villain_id);
        }

        while (resultSet.next()){
            if (resultSet.getRow() == 1){
                System.out.printf("Villain: %s%n", resultSet.getString(1));
            }
            System.out.printf("%d. %s %d%n", resultSet.getRow(), resultSet.getString(2), resultSet.getInt(3));
        }

    }
}

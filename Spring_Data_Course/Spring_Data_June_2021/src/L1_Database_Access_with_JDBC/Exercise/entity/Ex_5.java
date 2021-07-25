package L1_Database_Access_with_JDBC.Exercise.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ex_5 {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static void execute() throws IOException, SQLException {
        QueryGenerator queryGenerator = new QueryGenerator();
        System.out.println("Please input a country name for which to update towns to upper case:");
        String countryName = bf.readLine();

        int affectedTowns = queryGenerator.updateColumnToUpperCase("towns", "`name`", countryName, "country = ?");

        if (affectedTowns == 0){
            System.out.println("No town names were affected.");
            return;
        }else{
            System.out.printf("%d town names were affected.%n", affectedTowns);
        }

        ResultSet resultSet = queryGenerator.selectByFilterCondition("`name`", "towns", String.format("country = '%s'", countryName));

        List<String> updatedTowns = new ArrayList<>();

        while (resultSet.next()){
            updatedTowns.add(resultSet.getString("name"));
        }

        System.out.println(updatedTowns);
    }
}

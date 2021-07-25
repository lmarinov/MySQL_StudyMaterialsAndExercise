package L1_Database_Access_with_JDBC.Exercise.entity;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ex_7 {
    public static void execute() throws IOException, SQLException{
        QueryGenerator queryGenerator = new QueryGenerator();

        ResultSet resultSet = queryGenerator.selectByFilterCondition("`name`", "minions", "id > 0");

        List<String> allNames = new ArrayList<>();

        while (resultSet.next()){
            allNames.add(resultSet.getString("name"));
        }

        List<String> orderedNames = new ArrayList<>();

        int n = allNames.size() / 2;

        for (int i = 0; i < n; i++) {
            orderedNames.add(allNames.get(i));
            orderedNames.add(allNames.get(allNames.size() - i - 1));

            if (i == n - 1 && allNames.size() % 2 != 0){
                orderedNames.add(allNames.get(n));
            }
        }

        System.out.println(String.join(System.lineSeparator(), orderedNames));

    }
}

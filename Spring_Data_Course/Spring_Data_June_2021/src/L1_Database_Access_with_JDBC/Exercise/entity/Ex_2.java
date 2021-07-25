package L1_Database_Access_with_JDBC.Exercise.entity;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex_2 {

    public static void execute() throws IOException, SQLException {

        QueryGenerator queryGenerator = new QueryGenerator();

        ResultSet resultSet = queryGenerator.orderedSelectWithJoin("v.`name`, " +
                        "COUNT(DISTINCT mv.minion_id) AS `minion_count`",
                "villains",
                "JOIN minions_villains mv on v.id = mv.villain_id GROUP BY v.id HAVING `minion_count` > 15",
                "ORDER BY `minion_count` DESC");


        while (resultSet.next()){
            System.out.printf("%s %d %n", resultSet.getString(1), resultSet.getInt(2));

        }
    }
}

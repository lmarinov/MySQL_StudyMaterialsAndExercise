package L1_Database_Access_with_JDBC.Exercise.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class Ex_4 {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void execute() throws IOException, SQLException {

        System.out.println("Please enter minion information in the format {Minion: name age town}:");

        String[] minionInfo = bf.readLine().split("\s+");

        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String minionTown = minionInfo[3];

        QueryGenerator queryGenerator = new QueryGenerator();

        ResultSet resultSet = queryGenerator.selectByFilterCondition("id", "towns", String.format("LOWER(`name`) = '%s'", minionTown.toLowerCase(Locale.ROOT)));

        if (!resultSet.isBeforeFirst()){
            if (queryGenerator.insertInto("towns", "`name`", String.format("'%s'", minionTown)) > 0){
                System.out.printf("Town %s was added to the database.%n", minionTown);
            }
        }

        resultSet.close();
        resultSet = queryGenerator.selectByFilterCondition("id", "towns", String.format("LOWER(`name`) = '%s'", minionTown.toLowerCase(Locale.ROOT)));
        resultSet.next();

        int town_id = resultSet.getInt("id");

        resultSet.close();

        resultSet = queryGenerator.selectByFilterCondition("`name`", "minions", String.format("LOWER(`name`) = '%s'", minionName.toLowerCase(Locale.ROOT)));

        if (!resultSet.isBeforeFirst()){
           queryGenerator.insertInto("minions", "`name`, age, town_id", String.format("'%s', %d, %d", minionName, minionAge, town_id));
        }

        resultSet.close();

        System.out.println("Please enter villain information in the format {Villain: name}:");

        String[] villainInfo = bf.readLine().split("\s+");

        String villainName = villainInfo[1];

        resultSet = queryGenerator.selectByFilterCondition("id", "villains", String.format("LOWER(`name`) = '%s'", villainName.toLowerCase(Locale.ROOT)));

        if (!resultSet.isBeforeFirst()){
            if (queryGenerator.insertInto("villains", "`name`, evilness_factor", String.format("'%s', '%s'", villainName, "evil")) > 0){
                System.out.printf("Villain %s was added to the database.%n", villainName);
            }
        }

        resultSet.close();

        int minions_villains = queryGenerator.insertInto("minions_villains", "minion_id, villain_id", String.format("(SELECT `id` FROM minions WHERE `name` = '%s'), (SELECT `id` FROM villains WHERE `name` = '%s')", minionName, villainName));

        if (minions_villains > 0){
            System.out.printf("Successfully added %s to be minion of %s.%n", minionName, villainName);
        }
    }
}

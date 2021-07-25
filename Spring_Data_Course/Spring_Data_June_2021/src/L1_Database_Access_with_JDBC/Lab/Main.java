package L1_Database_Access_with_JDBC.Lab;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter your credentials:");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", sc.nextLine(), sc.nextLine());

        Statement statement = connection.createStatement();

        System.out.println("Please select salary you want to search for:");

        double salary =  Double.parseDouble(sc.nextLine());
//
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees WHERE job_title = 'Production Technician' AND salary > " + salary);
//
//        while(resultSet.next()){
//            String jobTitle = resultSet.getString(5);
//            long id = resultSet.getLong(1);
//            System.out.println(id + " | " + jobTitle);
//
//        }

        PreparedStatement stmt = connection.prepareStatement("SELECT first_name, last_name FROM employees WHERE salary > ?");

        stmt.setString(1, String.valueOf(salary));

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){

            System.out.println(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
        }


    }
}

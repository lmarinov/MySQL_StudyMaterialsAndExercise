package L1_Database_Access_with_JDBC.Exercise;

import L1_Database_Access_with_JDBC.Exercise.entity.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class Main {

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "minions_db";
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final Properties properties = new Properties();


    public static void main(String[] args){
        try {

        System.out.println("Please select exercise number:");
        int exNum = Integer.parseInt(bf.readLine());
            switch (exNum) {
                case 1 -> Ex_1.execute();
                case 2 -> Ex_2.execute();
                case 3 -> Ex_3.execute();
                case 4 -> Ex_4.execute();
                case 5 -> Ex_5.execute();
                case 6 -> Ex_6.execute();
                case 7 -> Ex_7.execute();
                case 8 -> Ex_8.execute();
                case 9 -> Ex_9.execute();
            }
        }catch (IOException | SQLException s){
            System.out.println(s.getMessage());
        }

    }

    public static Connection getConnection() throws SQLException, IOException {

        System.out.println("Please input your username:");
        properties.setProperty("user", bf.readLine());
        System.out.println("Please input your password:");
        properties.setProperty("password", bf.readLine());

        return DriverManager
                .getConnection(CONNECTION_STRING + DATABASE_NAME, properties);
    }
}

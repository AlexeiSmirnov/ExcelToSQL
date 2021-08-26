package SQL;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class TableActions {
    private static final String TABLE_HADINGS = "CREATE TABLE IF NOT EXISTS nalog1nom_Smirnov(fielda varchar(255) NULL," +
            "fieldb varchar(1000) NULL," +
            "fieldv varchar(255) NULL," +
            "field1 varchar(255) NULL," +
            "field2 varchar(255) NULL," +
            "field3 varchar(255) NULL," +
            "field4 varchar(255) NULL," +
            "field5 varchar(255) NULL," +
            "field6 varchar(255) NULL," +
            "field7 varchar(255) NULL," +
            "field8 varchar(255) NULL," +
            "field9 varchar(255) NULL," +
            "field10 varchar(255) NULL," +
            "field11 varchar(255) NULL," +
            "field12 varchar(255) NULL," +
            "field13 varchar(255) NULL," +
            "field14 varchar(255) NULL," +
            "field15 varchar(255) NULL," +
            "field16 varchar(255) NULL," +
            "field17 varchar(255) NULL," +
            "field18 varchar(255) NULL," +
            "field19 varchar(255) NULL," +
            "field20 varchar(255) NULL," +
            "field21 varchar(255) NULL," +
            "field22 varchar(255) NULL," +
            "field23 varchar(255) NULL," +
            "field24 varchar(255) NULL," +
            "field25 varchar(255) NULL," +
            "ter varchar(255) NULL," +
            "dat varchar(255) NULL)";
    private static String url;
    private static String user;
    private static String password;

    public void createTable() {
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");) {
            Properties prop = new Properties();
            prop.load(fileInputStream);
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                Statement statement = connection.createStatement();
                statement.execute(TABLE_HADINGS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addValues(List<List<String>> list) {

        String sqlQuery = "INSERT INTO nalog1nom_Smirnov VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        for (List<String> strings : list) {
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                for (int j = 0; j < strings.size(); j++) {
                    preparedStatement.setString(j + 1, strings.get(j));
                }
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}

package com.example.mywebsite;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Data Access Object for Users.
 */
public class UserDao {
    private final String port = "3306";
    private final String dbName = "usersdb";
    private final String dbUserName = "root";
    private final String dbPassword = "rootpassword";
    private final String DBPATH = "jdbc:mysql://localhost:" + port + "/" + dbName;

    private final String dbDriver = "com.mysql.jdbc.Driver";

    public UserDao() {


    }


    /**
     * Function to reverse the sql.Date in order to use util.Date
     *
     * @param sqlDate sql.Date to be reversed
     * @return util.Date
     */
    private java.util.Date reverseDate(java.sql.Date sqlDate) {
        java.util.Date utilDate = null;

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        String date = sqlDate.toString();
        String[] tempStrings = date.split("-");
        StringBuilder finalDate = new StringBuilder();
        for (String s : tempStrings) {
            finalDate.insert(0, s + "-");
        }
        finalDate.setLength(finalDate.length() - 1);

        try {
            utilDate = dateFormat.parse(finalDate.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return utilDate;
    }

    /**
     * Load the database driver
     *
     * @param dbDriver driver to be loaded
     */
    private void loadDriver(String dbDriver) {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Connecting to the database
     *
     * @return Connection object
     * @throws SQLException if unable to connect to the db
     */
    private Connection getConnection() {
        Connection connection;

        try {
            connection = DriverManager.getConnection(DBPATH, dbUserName, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    /**
     * Used to add a user in the database along with his home/work addresses.
     * A transaction is used to ensure there are no inconsistent data in case of interruption.
     *
     * @param user user to be added
     * @return true if successfully added the user in the database,
     * false if an error occurred.
     * @throws SQLException – if a database access error occurs or this method is called on a closed connection
     */
    private boolean addUserWithAddresses(User user) throws SQLException {
        loadDriver(dbDriver);
        Connection connection = getConnection();

        boolean success = true;
        System.out.println(user);
        String sqlQuery1 = "insert into users (`Name`, `Surname`, `BirthDate`, `Gender`) " +
                "  VALUES(?, ?, ?, ?) ";

        String sqlQuery2 = "insert into useraddress (`WorkAddress`, `HomeAddress`, `userid`)" +
                " values (?,?,LAST_INSERT_ID())";

        try {
            connection.setAutoCommit(false);

            // used due to parameters
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery1);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());

            java.sql.Date sqlDate = new Date(user.getBirthdate().getTime());
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setString(4, user.getGender());

            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(sqlQuery2);

            preparedStatement.setString(1, user.getWorkAddress());
            preparedStatement.setString(2, user.getHomeAddress());

            preparedStatement.executeUpdate();

            connection.commit();
            preparedStatement.close();


        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();

            success = false;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return success;

    }

    /**
     * Used to add a user in the database.
     *
     * @param user user to be added
     * @return true if successfully added the user in the database,
     * false if an error occurred.
     * @throws SQLException – if a database access error occurs or this method is called on a closed connection
     */
    private boolean addUser(User user) throws SQLException {
        loadDriver(dbDriver);
        Connection connection = getConnection();

        String sqlQuery = "INSERT INTO `usersdb`.`users` (`Name`, `Surname`, `BirthDate`, `Gender`)" +
                " VALUES (?,?,?,?)";

        boolean success = true;

        try {
            // used due to parameters
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());

            java.sql.Date sqlDate = new Date(user.getBirthdate().getTime());
            preparedStatement.setDate(3, sqlDate);

            preparedStatement.setString(4, user.getGender());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        } finally {
            connection.close();
        }

        return success;
    }

    /**
     * Used to add a user in the database. If there is a homeAddress or a workAddress addUserWithAddresses is called.
     * If not, addUser is called.
     *
     * @param user the user to be added.
     * @return true if successfully added the user in the database,
     * false if an error occurred.
     */
    public boolean add(User user) {
        boolean success;

        try {
            if (user.getWorkAddress().isBlank() && user.getHomeAddress().isBlank()) {
                success = addUser(user);
            } else {
                success = addUserWithAddresses(user);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong while adding the user.");
            throw new RuntimeException(e);
        }
        return success;
    }

    /**
     * Get all the user info in the database
     *
     * @return list with all the user info
     */
    public ArrayList<User> getUsersNames() throws SQLException {
        ArrayList<User> userList = new ArrayList<>();

        loadDriver(dbDriver);
        Connection connection = getConnection();


        try {
            String sqlQuery = "select * from users";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("Name"));
                user.setSurname(resultSet.getString("Surname"));
                java.sql.Date date = resultSet.getDate("BirthDate");
                user.setBirthdate(reverseDate(date));
                user.setGender(resultSet.getString("Gender"));

                userList.add(user);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return userList;
    }

    /**
     * Used to get all the info for user with the given name and surname.
     * @param name name of the user
     * @param surname surname of the user
     * @return User object if a match was found, null otherwise.
     */
    public User getUser(String name, String surname) throws SQLException {
        loadDriver(dbDriver);
        Connection connection = getConnection();
        User user = new User();
        try {
            String sqlQuery = "SELECT *\n" +
                    "FROM users\n" +
                    "LEFT OUTER JOIN useraddress\n" +
                    "ON users.UserID = useraddress.UserID\n" +
                    "where (users.Name = ? and users.Surname = ?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setName(resultSet.getString("Name"));
                user.setSurname(resultSet.getString("Surname"));

                java.util.Date date = reverseDate(resultSet.getDate("BirthDate"));
                user.setBirthdate(date);

                user.setGender(resultSet.getString("Gender"));
                user.setHomeAddress(resultSet.getString("HomeAddress"));
                user.setWorkAddress(resultSet.getString("WorkAddress"));
            }
            preparedStatement.close();
            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }

        return user;
    }

    /**
     * Used to delete the User with the given name and surname from the database
     * @param name name of the user
     * @param surname surname of the user
     */
    public void deleteUser(String name , String surname) throws SQLException {
        loadDriver(dbDriver);
        Connection connection = getConnection();
        String sqlQuery = "DELETE FROM `users` WHERE (`Name` = ? and `Surname` = ?);";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,surname);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }

    }

}

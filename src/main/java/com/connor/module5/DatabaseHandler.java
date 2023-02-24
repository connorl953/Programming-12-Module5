package com.connor.module5;

import java.sql.*;


public class DatabaseHandler {
    private static final String DB_url = "jdbc:derby:database/forum;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;

    public DatabaseHandler() {
        createConnection();
    }

    /**
     * login()
     * This method attempts to log in a user with the given username and password.
     *
     * @param username The username of the user attempting to log in.
     * @param password The password of the user attempting to log in.
     * @return A boolean value indicating whether the login was successful.
     * @throws SQLException If an error occurs while attempting to log in.
     */
    public boolean login(String username, String password) throws SQLException {
        stmt = conn.createStatement();
        String statement = "SELECT * FROM LOGINS WHERE USERNAME = '" + username + "' AND PASSWORD='" + password + "'";
        ResultSet rs = stmt.executeQuery(statement);

        return rs.next();
    }
    /**
     * register()
     * This method attempts to register a user with the given username and password.
     *
     * @param username The username of the user attempting to register.
     * @param password The password of the user attempting to register.
     * @return A boolean value indicating whether the registration was successful.
     * @throws SQLException If an error occurs while attempting to register.
     */
    public boolean register(String username, String password) throws SQLException {
        stmt = conn.createStatement();
        if(checkExists(username)) {
            return false;
        }
        String statement = "INSERT INTO LOGINS (USERNAME, PASSWORD) VALUES ('" + username + "','"+ password +"')";
        stmt.execute(statement);
        return true;
    }
    /**
     * checkExists()
     * This method checks if a user with the given username already exists.
     *
     * @param username The username of the user to check for.
     * @return A boolean value indicating whether the user exists.
     * @throws SQLException If an error occurs while attempting to check for the user.
     */
    public boolean checkExists(String username) throws SQLException {
        stmt = conn.createStatement();
        String statement = "SELECT * FROM LOGINS WHERE USERNAME='" + username + "'";
        ResultSet rs = stmt.executeQuery(statement);

        return rs.next();
    }

    /**
     *  Attempts to create a table the database for logins.
     */
    public void createTable() {

        String TABLE_NAME = "LOGINS";
        try {
            stmt = conn.createStatement();
            DatabaseMetaData dmn = conn.getMetaData();
            ResultSet tables = dmn.getTables(null, null, TABLE_NAME, null);
            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + " exists");
            } else {
                String statement = "CREATE TABLE " + TABLE_NAME + "(\n" +
                        "    USERNAME VARCHAR(200) NOT NULL PRIMARY KEY,\n" +
                        "    PASSWORD VARCHAR(200) NOT NULL\n" +
                        ")";

                System.out.println(statement);
                stmt.execute(statement);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * Creates a connection to the database using the org.apache.derby.jdbc.ClientDriver.
     */
    private void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection(DB_url);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Creates a new column in the specified table.
     *
     * @param TABLE_NAME the name of the table to add the column to
     * @param COLUMN_NAME the name of the column to add
     * @throws SQLException if an error occurs while creating the column

     */
    private void createTableColumn(String TABLE_NAME, String COLUMN_NAME) throws SQLException {
        COLUMN_NAME = COLUMN_NAME.toUpperCase();
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet rs = metaData.getColumns(null, null, TABLE_NAME, COLUMN_NAME);
        if (rs.next()) {
            System.out.println("Column " + COLUMN_NAME + " in table " + TABLE_NAME + " exists");

        } else {
            String statement = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_NAME + " varchar(200) ";
            System.out.println(statement);
            stmt.execute(statement);
        }

    }

    /**
     * Adds data to the specified table.
     *
     * @param TABLE_NAME the name of the table to add the data to
     * @param data the data to add to the table
     * @throws SQLException if an error occurs while adding the data
     */
    private void addData(String TABLE_NAME, String data) throws SQLException {
            String statement = "INSERT INTO " + TABLE_NAME + " VALUES " + "(" + data + ")";
            System.out.println(statement);
            stmt.execute(statement);
    }


}

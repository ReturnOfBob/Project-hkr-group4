package civ.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DataBaseConnector {
    //-----------------------------------VARIABLES--------------------------------------\\ 

    private final String URL = "jdbc:mysql://127.0.0.1:3306/civ-basic?user=root&password=root";
    private Connection c;
    private ResultSet rs;
    private PreparedStatement prepSt;
    
    //-----------------------------------DATABASE CODE--------------------------------------\\ 
    
    public Connection getConnection() throws SQLException {
        c = DriverManager.getConnection(URL);
        return c;
    }

    public ResultSet getResult(String command) throws SQLException {
        c = getConnection();
        prepSt = c.prepareStatement(command);
        rs = prepSt.executeQuery();
        return rs;
    }

    public PreparedStatement getDelete(String command) throws SQLException {
        c = getConnection();
        prepSt = c.prepareStatement(command);
        prepSt.executeUpdate(command);
        return prepSt;
    }

    public ResultSet getResultSet() throws SQLException{
        return rs;
    }

    public void close() throws SQLException {
        if (c != null) {
            c.close();
        }
        if (rs != null) {
            rs.close();
        }
        if (prepSt != null) {
            prepSt.close();
        }
    }
    //-----------------------------------MYSQL COMMANDS--------------------------------------\\ 
    
    public String getRescourseCommand(String attribute, String nameList, String rescourseNames) {
        String rescourseCommand = " SELECT " + attribute + " FROM buildings_manages_resources WHERE Buildings_Name = '" + nameList + "' AND Resources_Name = '" + rescourseNames + "'";
        return rescourseCommand;
    }

    public String getGenericAndCommand(String attribute, String table, String whereCondition, String name, String whereCondition2, String password) {
        String genericAndCommand = " SELECT " + attribute + " FROM " + table + " WHERE " + whereCondition + " = '" + name + "' AND " + whereCondition2 + " = '" + password + "'";
        return genericAndCommand;
    }

    public String getGenericHighScoreCommand(int roundLimit) {
        String genericHighScoreComand = "SELECT Accounts_Username,Score, Difficulty FROM leaderboard WHERE round_Limit = '" + roundLimit + "'ORDER BY Score DESC LIMIT 20";
        return genericHighScoreComand;
    }

    public String getDeleteFromTableCommand(String name, int score, String difficulty) {
        String deleteFromTableCommand = "DELETE FROM leaderboard WHERE Accounts_Username = '" + name + "' AND Score = '" + score + "' AND Difficulty = '" + difficulty + "'";
        return deleteFromTableCommand;
    }
    
    public String getOneAttributeCommand(String attribute, String table) {
        String oneAttributeCommand = "SELECT " + attribute + " FROM " + table + "";
        return oneAttributeCommand;
    }
    
    public <T> String getGenericCommand(String attribute, String table, String whereCondition,T value) {
        String genericCommand = " SELECT " + attribute + " FROM " + table + " WHERE " + whereCondition + " = '" + value + "'";
        return genericCommand;
    }
    
    public String getGenericInsertCommand(String table, String attributeOne, String attributeTwo, String attributeThree, String attributeFour, String attributeFive) {
        String insertHighScoreCommand = "INSERT INTO " + table + " (" + attributeOne + "," + attributeTwo + "," + attributeThree + "," + attributeFour + "," + attributeFive + ") VALUES (?,?,?,?,?)";
        return insertHighScoreCommand;
    }

}

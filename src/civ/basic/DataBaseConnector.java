/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civ.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Henrik
 */
public class DataBaseConnector {

    private final String URL = "jdbc:mysql://127.0.0.1:3306/civ-basic?user=root&password=root";
    private Connection c;
    private ResultSet rs;
    private PreparedStatement prepSt;
    private final String inputUserDataCommand = "INSERT INTO accounts (Username,Password,Security_Question,Answer,Privilege) VALUES (?,?,?,?,?)";

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

    public ResultSet getResultSet() {
        return rs;
    }

    public String getUserLoginCheckCommand(String name, String password) {
        String userLoginCheck = "SELECT * FROM accounts WHERE Username = '" + name + "' AND Password = '" + password + "'";
        return userLoginCheck;
    }

    public String getInputUserDataCommand() {
        return inputUserDataCommand;
    }

    public String getAllUserInfoCommand(String name) {
        String allUserInfo = "SELECT * FROM accounts WHERE Username = '" + name + "'";
        return allUserInfo;
    }

    public String getSecuityQuestionCommand(String name) {
        String secuityQuestion = "SELECT Security_Question FROM accounts WHERE Username = '" + name + "'";
        return secuityQuestion;
    }

    public String getSecurityQuestionAnswerCommand(String name) {
        String questionAnswer = "SELECT Answer FROM accounts WHERE Username = '" + name + "'";
        return questionAnswer;
    }

    public String getUserPasswordCommand(String name) {
        String userPasswordCommand = "SELECT Password FROM accounts WHERE Username = '" + name + "'";
        return userPasswordCommand;
    }
    public String getScoreCommand(String name){
        String insertScoreCommand = "SELECT UserName AND Score FROM leaderboard WHERE UserName =  '" + name + "'";
        return insertScoreCommand;
    }
}

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

}

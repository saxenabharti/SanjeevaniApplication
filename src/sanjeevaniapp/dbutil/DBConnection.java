/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanjeevaniapp.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class DBConnection {
    private static Connection conn;
    static{
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded Successfully!");
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
            System.exit(0);
        }
        try{
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","sanjeevaniapp","abcd");
             System.out.println("connection open successfully");
        }catch(SQLException ex){
            ex.printStackTrace();
            System.exit(0);
        }
    }
    public static Connection getConnection(){
        return conn;
    }
    public static void closeConnection(){
        if(conn!=null){
            try{
                conn.close();
                System.out.println("conn close successfully");
            }catch(SQLException ex ){
                ex.printStackTrace();
            }
        }
    }
}

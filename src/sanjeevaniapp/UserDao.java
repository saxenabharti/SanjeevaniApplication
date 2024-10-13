/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanjeevaniapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sanjeevaniapp.dbutil.DBConnection;
import sanjeevaniapp.pojo.ReceptionistPojo;
import sanjeevaniapp.pojo.User;
import sanjeevaniapp.pojo.UserPojo;

/**
 *
 * @author hp
 */
public class UserDao {
    public static String validateUser (User user)throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps =conn.prepareStatement("select user_name from users where login_id=? and password = ? and user_type=?");
        ps.setString(1, user.getLoginId());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getUserType());
        ResultSet rs = ps.executeQuery();
        String name = null;
        if(rs.next()){
            name = rs.getString("user_name");
        }
        return name;
    }
    public static void updateName(String currname,String newname)throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update users set user_name = ? where user_name = ?");
        ps.setString(1, newname);
        ps.setString(2, currname);
        ps.executeUpdate();
    }
    public static void removeUserByName(String userName )throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from users where USER_NAME =?");
        ps.setString(1, userName);
        ps.executeUpdate();
    }
    public static boolean addUser(UserPojo user)throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into users values(?,?,?,?)");
        ps.setString(1, user.getLoginId());
        ps.setString(2, user.getUserName());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getUsertype().toUpperCase());
        return ps.executeUpdate()==1;
    }
    public static void deleteUserByName(String name)throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from users where user_name= ?");
        ps.setString(1, name);
        ps.executeUpdate();
    }
    public static String getuserLoginId(String userName)throws SQLException{
       Connection conn = DBConnection.getConnection();
       PreparedStatement ps = conn.prepareStatement("Select * from users where user_name=? and user_type='RECEPTIONIST'");
       ps.setString(1, userName);
     ResultSet rs = ps.executeQuery();
     String name = null;
     if(rs.next())
     {
         name=rs.getString(1);
     }
     return name;
    }
    public static void updateUser(UserPojo user,String currname)throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update users set login_id = ? , user_name = ?,password = ? where user_name = ?");
        ps.setString(1, user.getLoginId());
        ps.setString(2, user.getUserName());
        ps.setString(3, user.getPassword());
        ps.setString(4, currname);
        ps.executeUpdate();
    }
}
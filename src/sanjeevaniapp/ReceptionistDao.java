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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sanjeevaniapp.dbutil.DBConnection;
import sanjeevaniapp.pojo.ReceptionistPojo;
import sanjeevaniapp.pojo.UserPojo;

/**
 *
 * @author hp
 */
public class ReceptionistDao {
    public static void updateName(String currname , String newname)throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update receptionists set receptionist_name = ? where receptionist_name = ?");
        ps.setString(1, newname);
        ps.setString(2, currname);
        ps.executeUpdate();
    }
    public static void removeReceptionistByName(String userName )throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from receptionists where  RECEPTIONIST_NAME =?");
        ps.setString(1, userName);
        ps.executeUpdate();
    }
    public static boolean addReceptionist(ReceptionistPojo rec)throws SQLException{
       Connection conn = DBConnection.getConnection();
       PreparedStatement ps = conn.prepareStatement("insert into receptionists values(?,?,?)");
       ps.setString(1, rec.getReceptionistId());
       ps.setString(2, rec.getReceptionistName());
       ps.setString(3, rec.getGender());
       return ps.executeUpdate()==1;
    }
    public static String getNewRecId()throws  SQLException{
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select max(receptionist_id) from receptionists");
        rs.next();
        int recId = 101;
        String id = rs.getString(1);
        if(id!=null){
            String num = id.substring(3);
            recId = Integer.parseInt(num)+1;   
        }
               return "Rec"+recId;
    }
    public static List<String> getAllReceptionistId()throws SQLException{
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select Receptionist_id from receptionists");
        List<String> recepList = new ArrayList<>();
        while(rs.next()){
            recepList.add(rs.getString(1));
        }
        return recepList;
    }
    public static boolean deleteReceptionistById(String recId)throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select Receptionist_name from receptionists where receptionist_id=?");
        ps.setString(1, recId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String recName = rs.getString(1);
        UserDao.deleteUserByName(recName);
        ps = conn.prepareStatement("delete from receptionists where receptionist_id=?");
        ps.setString(1, recId);
        return ps.executeUpdate()==1;
    }
    public static List<ReceptionistPojo> getAllReceptionistDetails()throws SQLException{
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from receptionists order by receptionist_id");
        List<ReceptionistPojo> recList = new ArrayList<>();
        while(rs.next()){
           ReceptionistPojo rec=new ReceptionistPojo();
           rec.setReceptionistId(rs.getString(1));
           rec.setReceptionistName(rs.getString(2));
           rec.setGender(rs.getString(3));
           recList.add(rec);
        }
        return recList;
    }
    public static ReceptionistPojo getReceptionistDetails(String recId)throws SQLException{
       Connection conn = DBConnection.getConnection();
       PreparedStatement ps = conn.prepareStatement("select * from receptionists where receptionist_id=?");
       ps.setString(1, recId);
       ResultSet rs = ps.executeQuery();
       rs.next();
       ReceptionistPojo rec = new ReceptionistPojo();
       rec.setReceptionistId(rs.getString(1));
       rec.setReceptionistName(rs.getString(2));
       rec.setGender(rs.getString(3));
       return rec;
   }
    public static boolean updateReceptionist(ReceptionistPojo rec, UserPojo user)throws SQLException{
        String currname = updateReceptionistByName(rec);
        UserDao.updateUser(user,currname);
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update receptionists set receptionist_name = ? where receptionist_id = ?");
        ps.setString(1, rec.getReceptionistName());
        ps.setString(2, rec.getReceptionistId());
        return ps.executeUpdate()==1;
    }
    private static String updateReceptionistByName(ReceptionistPojo rec)throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select receptionist_name from receptionists where receptionist_id = ?");
        ps.setString(1, rec.getReceptionistId());
        ResultSet rs = ps.executeQuery();
        rs.next();
        String currname = rs.getString(1);
        String newName = rec.getReceptionistName();
        EmpDao.updateEmpName(currname, newName);
        return currname;
    }
}

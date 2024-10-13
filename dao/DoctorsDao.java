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
import sanjeevaniapp.pojo.DoctorsPojo;

/**
 *
 * @author hp
 */
public class DoctorsDao {
    public static void updateName(String currname,String newname)throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update doctors set doctor_name=? where doctor_name =?");
        ps.setString(1, newname);
        ps.setString(2, currname);
        ps.executeUpdate();
    }
     public static void removeDoctorByName(String userName )throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from doctors where DOCTOR_NAME =?");
        ps.setString(1, userName);
        ps.executeUpdate();
    }
    public static boolean addDoctor(DoctorsPojo doc)throws SQLException{
       Connection conn = DBConnection.getConnection();
       PreparedStatement ps = conn.prepareStatement("insert into doctors values(?,?,?,?,?,?,?)");
       ps.setString(1, doc.getDoctorId());
       ps.setString(2, doc.getDoctorName());
       ps.setString(3, doc.getEmailId());
       ps.setString(4, doc.getContactNo());
       ps.setString(5, doc.getQualification());
       ps.setString(6, doc.getGender());
       ps.setString(7, doc.getSpecialist());
       return ps.executeUpdate()==1;
    }
    public static String getNewDocId()throws  SQLException{
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select max(doctor_id) from doctors");
        rs.next();
        int docId = 101;
        String id = rs.getString(1);
        if(id!=null){
            String num = id.substring(3);
            docId = Integer.parseInt(num)+1;   
        }
               return "Doc"+docId;
    }
    public static List<String> getAllDoctorId()throws SQLException{
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select doctor_id from doctors");
        List<String> doctorList = new ArrayList<>();
        while(rs.next()){
            doctorList.add(rs.getString(1));
        }
        return doctorList;
    }
    public static boolean deleteDoctorById(String docId)throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select doctor_name from doctors where doctor_id=?");
        ps.setString(1, docId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String docName = rs.getString(1);
        UserDao.deleteUserByName(docName);
        ps = conn.prepareStatement("delete from doctors where doctor_id=?");
        ps.setString(1, docId);
        return ps.executeUpdate()==1;
    }
    public static List<DoctorsPojo> getAllDoctorDetails()throws SQLException{
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from doctors order by doctor_id");
        List<DoctorsPojo> docList = new ArrayList<>();
        while(rs.next()){
           DoctorsPojo doc=new DoctorsPojo();
           doc.setDoctorName(rs.getString("doctor_name"));
           doc.setContactNo(rs.getString("contact_no"));
           doc.setDoctorId(rs.getString("doctor_id"));
           doc.setEmailId(rs.getString("email_id"));
           doc.setQualification(rs.getString("qualification"));
           doc.setGender(rs.getString("gender"));
           doc.setSpecialist(rs.getString("specialist"));
           docList.add(doc);
        }
        return docList;
    }
    public static String getDoctorNameById(String docId)throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select doctor_name from doctors where doctor_id=?");
        ps.setString(1, docId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String name = rs.getString(1);
        return name;
    }
}

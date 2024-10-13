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
import sanjeevaniapp.pojo.AppointmentPojo;
import sanjeevaniapp.pojo.PatientPojo;

/**
 *
 * @author hp
 */
public class AppointmentDao {
    public static boolean addAppointment(AppointmentPojo appt)throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into appointments values(?,?,?,?,?,?,?)");
        ps.setString(1, appt.getPatientId());
        ps.setString(2, appt.getPatientName());
        ps.setString(3, appt.getStatus());
        ps.setString(4,appt.getOpd());
        ps.setString(5, appt.getAppointmentDate());
        ps.setString(6,appt.getDoctorName());
        ps.setString(7, appt.getMobileNo());
        return ps.executeUpdate()==1;
    }
    public static List<AppointmentPojo>getAllAppointmentsByDoctorName(String doctorName)throws SQLException{
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from appointments where doctor_name = ? and status = 'REQUEST' order by patient_id");
        ps.setString(1, doctorName);
        ResultSet rs = ps.executeQuery();
        List<AppointmentPojo> appList = new ArrayList<>();
        while(rs.next()){
            AppointmentPojo app = new AppointmentPojo();
            app.setPatientId(rs.getString("patient_id"));
            app.setPatientName(rs.getString("patient_name"));
            app.setOpd(rs.getString("opd"));
            app.setAppointmentDate(rs.getString("date_time"));
            app.setStatus(rs.getString("status"));
            app.setMobileNo(rs.getString("mobile_no"));
            appList.add(app);
        }
        return appList;
    }
    public static boolean updateStatus(AppointmentPojo app)throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update appointments set status = ?,date_time = ? where patient_id = ?");
        ps.setString(1, app.getStatus());
        ps.setString(2,app.getAppointmentDate() );
        ps.setString(3, app.getPatientId());
        return ps.executeUpdate()==1;
    }
    public static List<AppointmentPojo> getAllConfirmPatientDetails(String docName)throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from appointments where doctor_name= ? and status=? order by patient_id ");
        ps.setString(1, docName);
        ps.setString(2, "CONFIRM");
        ResultSet rs = ps.executeQuery();
        List<AppointmentPojo> apptList = new ArrayList<>();
        while(rs.next()){
            AppointmentPojo appt = new AppointmentPojo();
            appt.setPatientId(rs.getString("patient_id"));
            appt.setPatientName(rs.getString("patient_name"));
            appt.setOpd(rs.getString("opd"));
            appt.setStatus(rs.getString("status"));
            apptList.add(appt);
        }
        return apptList;
    }
    public static boolean deletePatientById(String patId)throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from appointments where patient_id = ?");
        ps.setString(1, patId);
        return ps.executeUpdate()==1 ;
    }
    
    public static boolean updatePatient(PatientPojo pat)throws SQLException{
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("update appointments set patient_name=? , opd= ? ,mobile_no =? where patient_id =? ");
        ps.setString(1,pat.getFirstName()+" "+pat.getLastName());
        ps.setString(2, pat.getOpd());
        ps.setString(3, pat.getMno());
        ps.setString(4, pat.getPatientId());
        return ps.executeUpdate()==1;
    }
}

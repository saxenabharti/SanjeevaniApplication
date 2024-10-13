/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanjeevaniapp.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import sanjeevaniapp.dbutil.DBConnection;
import sanjeevaniapp.pojo.EmpPojo;

/**
 *
 * @author hp
 */
public class EmpDao {
    public static String getNextEmpId()throws SQLException{
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select max(emp_id) from employees ");
        rs.next();
        String str = rs.getString(1);
        int empId =101;
        if(str!=null){
            String id = str.substring(1);
            empId = Integer.parseInt(id);
            empId++;
        }
        String newId = "E"+empId;
        return newId;
    }
    public static boolean AddEmp(EmpPojo emp)throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into employees values(?,?,?,?)");
        ps.setString(1, emp.getEmpId());
        ps.setString(2, emp.getEmpName());
        ps.setString(3, emp.getEmpDept());
        ps.setDouble(4, emp.getEmpSal());
        return ps.executeUpdate()==1;
    }
    public static List<String> getAllEmployeeId()throws SQLException{
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select emp_id from employees");
        List<String> empIdList = new ArrayList<>();
        while (rs.next()){
           empIdList.add(rs.getString(1));
        }
        return empIdList;
    }
   public static EmpPojo getEmployeeDetails(String empId)throws SQLException{
       Connection conn = DBConnection.getConnection();
       PreparedStatement ps = conn.prepareStatement("select * from employees where emp_id=?");
       ps.setString(1, empId);
       ResultSet rs = ps.executeQuery();
       rs.next();
       EmpPojo emp = new EmpPojo();
       emp.setEmpId(rs.getString(1));
       emp.setEmpName(rs.getString(2));
       emp.setEmpDept(rs.getString(3));
       emp.setEmpSal(rs.getDouble(4));
       return emp;
   }
  public static boolean updateEmployee(EmpPojo emp)throws SQLException{    
      updateName(emp);
      Connection conn =DBConnection.getConnection();
      PreparedStatement ps = conn.prepareStatement("update employees set emp_name = ? , emp_salary =? where emp_id =? ");
      ps.setString(1, emp.getEmpName());
      ps.setDouble(2, emp.getEmpSal());
      ps.setString(3, emp.getEmpId());
      return ps.executeUpdate()==1;
}

    private static void updateName(EmpPojo emp)throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select emp_name from employees where emp_id =?");
        ps.setString(1, emp.getEmpId());
        ResultSet rs = ps.executeQuery();
        rs.next();
        String currname = rs.getString(1);
        String newName = emp.getEmpName();
        UserDao.updateName(currname, newName);
        if(emp.getEmpDept().equalsIgnoreCase("Receptionist"))
            ReceptionistDao.updateName(currname, newName);
        else if(emp.getEmpName().equalsIgnoreCase("Doctor"))
            DoctorsDao.updateName(currname, newName);
    }
    public static List<EmpPojo> getAllEmployeeDetails()throws SQLException{
       Connection conn = DBConnection.getConnection();
       Statement st = conn.createStatement();
       ResultSet rs = st.executeQuery("select * from employees order by emp_id");
       List<EmpPojo> empList = new ArrayList<>();
       while(rs.next()){
       EmpPojo emp = new EmpPojo();
       emp.setEmpId(rs.getString(1));
       emp.setEmpName(rs.getString(2));
       emp.setEmpDept(rs.getString(3));
       emp.setEmpSal(rs.getDouble(4));
       empList.add(emp);
       }
       return empList;
   }
     public static boolean RemoveEmp(String empId)throws SQLException{
        removeEmpByName(empId);
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from employees where emp_id=?");
        ps.setString(1, empId);
        return ps.executeUpdate()==1;
    }

    private static void removeEmpByName(String empId)throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select emp_name , emp_department from employees where emp_id =?");
        ps.setString(1, empId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String name = rs.getString(1);
        String dept = rs.getString(2);
        UserDao.removeUserByName(name);
        if(dept.equalsIgnoreCase("Receptionist"))
            ReceptionistDao.removeReceptionistByName(name);
        else if(dept.equalsIgnoreCase("Doctor"))
            DoctorsDao.removeDoctorByName(name);
    }
    public static Map<String,String>getUnRegisteredDoctors()throws SQLException{
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select emp_id , emp_name from employees where emp_department = 'DOCTOR' and emp_name not in (select user_name from users where user_type = 'DOCTOR')order by emp_id");
        Map<String,String> unRegDocList = new HashMap<>();
        while(rs.next()){
            String id = rs.getString(1);
            String name = rs.getString(2);
            unRegDocList.put(id, name);
        }
        return unRegDocList;
    } 
    public static Map<String,String>getUnRegisteredReceptionists()throws SQLException{
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select emp_id , emp_name from employees where emp_department = 'RECEPTIONIST' and emp_name not in (select user_name from users where user_type = 'RECEPTIONIST')order by emp_id");
        Map<String,String> unRegRecepList = new HashMap<>();
        while(rs.next()){
            String id = rs.getString(1);
            String name = rs.getString(2);
            unRegRecepList.put(id, name);
        }
        return unRegRecepList;
    } 
    public static void updateEmpName(String currname , String newname)throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update employees set emp_name = ? where emp_name = ?");
        ps.setString(1, newname);
        ps.setString(2, currname);
        ps.executeUpdate();
    }
    }

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanjeevaniapp.pojo;

/**
 *
 * @author hp
 */
public class DoctorsPojo {

    public DoctorsPojo(String doctorId, String doctorName, String emailId, String contactNo, String Qualification, String gender, String specialist) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.emailId = emailId;
        this.contactNo = contactNo;
        this.Qualification = Qualification;
        this.gender = gender;
        this.specialist = specialist;
    }
    public DoctorsPojo(){
        
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String Qualification) {
        this.Qualification = Qualification;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }
    private String doctorId;
    private String doctorName;
    private String emailId;
    private String contactNo;
    private String Qualification;
    private String gender;
    private String specialist;
}

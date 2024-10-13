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
public class UserPojo {

    public UserPojo(String loginId, String userName, String password, String usertype) {
        this.loginId = loginId;
        this.userName = userName;
        this.password = password;
        this.usertype = usertype;
    }
    
    public UserPojo(){
        
    }
    
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
    private String loginId;
    private String userName;
    private String password;
    private String usertype;
}

package managedbean;

import entity.MTLUser;
import error.GeneralException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import session.UserSessionLocal;

/**
 *
 * @author yeokw | Yeo Kian Wee | A0162262M
 */
@ManagedBean
@SessionScoped
public class AuthenticationManagedBean implements Serializable {

    private String username = null;
    private String password = null;
    private Long userId = -1L;
    
    @EJB
    private UserSessionLocal userSessionLocal;
    
    public AuthenticationManagedBean() {
    }
    
    public String login() throws GeneralException{
        //by right supposed to use a session bean to 
        //do validation here
        MTLUser u = userSessionLocal.userLogin(username, password);
        if (u != null) {
            //login successful            
            //store the logged in user id
            userId = u.getUserId();
            
            //do redirect
            return "/secret/secret.xhtml?faces-redirect=true";
        } else {
            //login failed
            username = null;
            password = null;
            userId = -1L;
            return "login.xhtml";
        }
        
//        //simulate username/password
//        if (username.equals("user1") && password.equals("password")){
//            //login successful
//            
//            //store the logged in user id
//            userId = 10;
//            
//            //do redirect
//            return "/secret/secret.xhtml?faces-redirect=true";
//        }
//        else{
//            //login failed
//            username = null;
//            password = null;
//            userId = -1;
//            return "login.xhtml";
//        }
    }
    
    public String logout(){
        username = null;
        password = null;
        userId = -1L;
        
        return "/login.xhtml?faces-redirect=true";
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
}

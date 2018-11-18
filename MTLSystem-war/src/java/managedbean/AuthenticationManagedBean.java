package managedbean;

import entity.MTLUser;
import error.GeneralException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import session.UserSessionLocal;

/**
 *
 * @author yeokw | Yeo Kian Wee | A0162262M
 */
@ManagedBean(name="authenticationManagedBean")
@SessionScoped
public class AuthenticationManagedBean implements Serializable {

    private String username = null;
    private String password = null;
//    private String firstName = null;
    private Long userId = -1L;
    
    @EJB
    private UserSessionLocal userSessionLocal;
    
    public AuthenticationManagedBean() {
    }
    
    @PostConstruct    
    public void init() {        
    }
    
    public String login() throws GeneralException {
        FacesContext context = FacesContext.getCurrentInstance();
        MTLUser u = userSessionLocal.userLogin(username, password);
        if (u != null) {
            //login successful            
            //store the logged in user id
            userId = u.getUserId();
//            firstName = u.getFirstName();

            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.setAttribute("userId", userId);
//            session.setAttribute("firstName", firstName);
            
            // Reset login fields            
            setUsername(null);
            setPassword(null);

            //do redirect
            return "searchUsers.xhtml?faces-redirect=true";
        } else {
            //login failed
            setUsername(null);
            setPassword(null);
            userId = -1L;
            return "login.xhtml?faces-redirect=true";
        }
    }
    
    public String logout(){
        username = null;
        password = null;
//        firstName = null;
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

//    /**
//     * @return the firstName
//     */
//    public String getFirstName() {
//        return firstName;
//    }
//
//    /**
//     * @param firstName the firstName to set
//     */
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//    
}

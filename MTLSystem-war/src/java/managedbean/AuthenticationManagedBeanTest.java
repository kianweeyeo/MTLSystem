package managedbean;

import entity.MTLUser;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import session.UserSessionLocal;

/**
 *
 * @author yeokw | Yeo Kian Wee | A0162262M
 */
@ManagedBean
@RequestScoped
public class AuthenticationManagedBeanTest implements Serializable {

    private String username = null;
    private String password = null;
    private Long userId = -1L;
    
    @EJB
    private UserSessionLocal userSessionLocal;
    
    public AuthenticationManagedBeanTest() {
    }
    
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        MTLUser u = userSessionLocal.userLogin(username, password);
        if (u != null) {
            //login successful            
            //store the logged in user id
            userId = u.getUserId();

            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.setAttribute("userId", userId);

            //do redirect
            return "/secret/secret.xhtml?faces-redirect=true";
        } else {
            //login failed
            setUsername(null);
            setPassword(null);
            userId = -1L;
            return "login.xhtml";
        }
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();

        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.setAttribute("userId", null);
        return "index?faces-redirect=true";
    }

    public int getUserId() {
        FacesContext context = FacesContext.getCurrentInstance();

        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Integer userId = (Integer)session.getAttribute("userId");

        if (userId != null) {
            return userId;
        }
        else{
            return 0;
        }
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
}
package singleton;

import entity.MTLUser;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yeokw | Yeo Kian Wee | A0162262M
 */
@Singleton
@LocalBean
@Startup
public class DataInitialisationSessionBean {

    @PersistenceContext(unitName = "MTLSystem-ejbPU")
    private EntityManager em;
    
    public DataInitialisationSessionBean() {
    }
    
    @PostConstruct
    public void postConstruct() {
        if (em.find(MTLUser.class, 1L) == null) {
            initialiseData();
        }
    }
    
    private void initialiseData() {
        MTLUser systemAdmin = new MTLUser("admin", "admin", "John", "Tan", new Date(1990, 12, 25), true, false, false);
        em.persist(systemAdmin);
    }
}
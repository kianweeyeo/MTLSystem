package singleton;

import entity.MTLUser;
import entity.PurchaseOrder;
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
        MTLUser systemAdmin = new MTLUser("admin", "admin", "John", "Tan", 91234567, "john@gmail.com", new Date(1990, 12, 25), true, false, false);
        em.persist(systemAdmin);
        
        MTLUser bobbyTan = new MTLUser("bobbytan", "pass", "Bobby", "Tan", 91112222, "bobbytan@gmail.com", new Date(1980, 12, 12), false, true, true);
        em.persist(bobbyTan);
        
        PurchaseOrder sampleOrder = new PurchaseOrder("Payment Confirmed", "Item is great! Fast delivery!", 5, 2L);
        em.persist(sampleOrder);
    }
}

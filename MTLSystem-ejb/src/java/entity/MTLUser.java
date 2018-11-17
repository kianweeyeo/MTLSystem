package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author yeokw | Yeo Kian Wee | A0162262M
 */
@Entity
public class MTLUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private boolean isAdmin;
    private boolean isSeller;
    private boolean isBuyer;
    private boolean isActive;
    @Temporal(TemporalType.DATE)
    private Date userCreated;
    
    // RELATIONSHIPS
    @OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
    private ArrayList<Item> sellerItemList;
    @OneToOne(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
    private Cart cart;
    @OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
    private ArrayList<PurchaseOrder> buyerOrderList;

    public MTLUser() {
    }

    public MTLUser(String username, String password, String firstName, String lastName, Date dateOfBirth, boolean isAdmin, boolean isSeller, boolean isBuyer) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.isAdmin = isAdmin;
        this.isSeller = isSeller;
        this.isBuyer = isBuyer;
        this.isActive = true;
        this.userCreated = new Date();
        this.sellerItemList = new ArrayList<Item>();
        this.cart = new Cart();
        this.buyerOrderList = new ArrayList<PurchaseOrder>();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MTLUser)) {
            return false;
        }
        MTLUser other = (MTLUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.User[ id=" + userId + " ]";
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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the isAdmin
     */
    public boolean isIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * @return the isSeller
     */
    public boolean isIsSeller() {
        return isSeller;
    }

    /**
     * @param isSeller the isSeller to set
     */
    public void setIsSeller(boolean isSeller) {
        this.isSeller = isSeller;
    }

    /**
     * @return the isBuyer
     */
    public boolean isIsBuyer() {
        return isBuyer;
    }

    /**
     * @param isBuyer the isBuyer to set
     */
    public void setIsBuyer(boolean isBuyer) {
        this.isBuyer = isBuyer;
    }

    /**
     * @return the isActive
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * @return the userCreated
     */
    public Date getUserCreated() {
        return userCreated;
    }

    /**
     * @param userCreated the userCreated to set
     */
    public void setUserCreated(Date userCreated) {
        this.userCreated = userCreated;
    }

    /**
     * @return the sellerItemList
     */
    public ArrayList<Item> getSellerItemList() {
        return sellerItemList;
    }

    /**
     * @param sellerItemList the sellerItemList to set
     */
    public void setSellerItemList(ArrayList<Item> sellerItemList) {
        this.sellerItemList = sellerItemList;
    }

    /**
     * @return the cart
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * @param cart the cart to set
     */
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /**
     * @return the buyerOrderList
     */
    public ArrayList<PurchaseOrder> getBuyerOrderList() {
        return buyerOrderList;
    }

    /**
     * @param buyerOrderList the buyerOrderList to set
     */
    public void setBuyerOrderList(ArrayList<PurchaseOrder> buyerOrderList) {
        this.buyerOrderList = buyerOrderList;
    }
    
}

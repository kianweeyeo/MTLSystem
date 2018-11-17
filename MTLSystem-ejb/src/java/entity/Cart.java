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
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    private int creditCardNum;
    @Temporal(TemporalType.DATE)
    private Date creditCardExpiry;
    private int creditCardCvv;
    private boolean paymentSuccess;
    
    // RELATIONSHIPS
    @OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
    private ArrayList<Item> cartItemList;

    public Cart() {
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cartId != null ? cartId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.cartId == null && other.cartId != null) || (this.cartId != null && !this.cartId.equals(other.cartId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Cart[ id=" + cartId + " ]";
    }

    /**
     * @return the creditCardNum
     */
    public int getCreditCardNum() {
        return creditCardNum;
    }

    /**
     * @param creditCardNum the creditCardNum to set
     */
    public void setCreditCardNum(int creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    /**
     * @return the creditCardExpiry
     */
    public Date getCreditCardExpiry() {
        return creditCardExpiry;
    }

    /**
     * @param creditCardExpiry the creditCardExpiry to set
     */
    public void setCreditCardExpiry(Date creditCardExpiry) {
        this.creditCardExpiry = creditCardExpiry;
    }

    /**
     * @return the creditCardCvv
     */
    public int getCreditCardCvv() {
        return creditCardCvv;
    }

    /**
     * @param creditCardCvv the creditCardCvv to set
     */
    public void setCreditCardCvv(int creditCardCvv) {
        this.creditCardCvv = creditCardCvv;
    }

    /**
     * @return the paymentSuccess
     */
    public boolean isPaymentSuccess() {
        return paymentSuccess;
    }

    /**
     * @param paymentSuccess the paymentSuccess to set
     */
    public void setPaymentSuccess(boolean paymentSuccess) {
        this.paymentSuccess = paymentSuccess;
    }

    /**
     * @return the cartItemList
     */
    public ArrayList<Item> getCartItemList() {
        return cartItemList;
    }

    /**
     * @param cartItemList the cartItemList to set
     */
    public void setCartItemList(ArrayList<Item> cartItemList) {
        this.cartItemList = cartItemList;
    }
    
}

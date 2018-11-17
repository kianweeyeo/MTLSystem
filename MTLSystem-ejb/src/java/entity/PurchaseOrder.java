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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author yeokw | Yeo Kian Wee | A0162262M
 */
@Entity
public class PurchaseOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;    
    private String orderStatus;
    private String feedbackReview;
    private int feedbackRating;    
    @Temporal(TemporalType.DATE)
    private Date orderCreated;
    private Long orderSellerId;
    
    // RELATIONSHIPS
    @OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
    private ArrayList<Item> orderItemList;

    public PurchaseOrder() {
    }    
    
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseOrder)) {
            return false;
        }
        PurchaseOrder other = (PurchaseOrder) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Order[ id=" + orderId + " ]";
    }
    
    /**
     * @return the feedbackReview
     */
    public String getFeedbackReview() {
        return feedbackReview;
    }

    /**
     * @param feedbackReview the feedbackReview to set
     */
    public void setFeedbackReview(String feedbackReview) {
        this.feedbackReview = feedbackReview;
    }

    /**
     * @return the feedbackRating
     */
    public int getFeedbackRating() {
        return feedbackRating;
    }

    /**
     * @param feedbackRating the feedbackRating to set
     */
    public void setFeedbackRating(int feedbackRating) {
        this.feedbackRating = feedbackRating;
    }

    /**
     * @return the orderCreated
     */
    public Date getOrderCreated() {
        return orderCreated;
    }

    /**
     * @param orderCreated the orderCreated to set
     */
    public void setOrderCreated(Date orderCreated) {
        this.orderCreated = orderCreated;
    }

    /**
     * @return the orderItemList
     */
    public ArrayList<Item> getOrderItemList() {
        return orderItemList;
    }

    /**
     * @param orderItemList the orderItemList to set
     */
    public void setOrderItemList(ArrayList<Item> orderItemList) {
        this.orderItemList = orderItemList;
    }

    /**
     * @return the orderStatus
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus the orderStatus to set
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @return the orderSellerId
     */
    public Long getOrderSellerId() {
        return orderSellerId;
    }

    /**
     * @param orderSellerId the orderSellerId to set
     */
    public void setOrderSellerId(Long orderSellerId) {
        this.orderSellerId = orderSellerId;
    }
    
}

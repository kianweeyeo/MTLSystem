package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author yeokw | Yeo Kian Wee | A0162262M
 */
@Entity
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private String name;
    private String description;
    private int quantity;
    private String category;
    private double price;
    @Temporal(TemporalType.DATE)
    private Date itemCreated;
    private Long itemSellerId;
    
    // RELATIONSHIPS
    @ManyToOne(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
    private PurchaseOrder order;

    public Item() {
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Item[ id=" + itemId + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the itemCreated
     */
    public Date getItemCreated() {
        return itemCreated;
    }

    /**
     * @param itemCreated the itemCreated to set
     */
    public void setItemCreated(Date itemCreated) {
        this.itemCreated = itemCreated;
    }

    /**
     * @return the order
     */
    public PurchaseOrder getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(PurchaseOrder order) {
        this.order = order;
    }

    /**
     * @return the itemSellerId
     */
    public Long getItemSellerId() {
        return itemSellerId;
    }

    /**
     * @param itemSellerId the itemSellerId to set
     */
    public void setItemSellerId(Long itemSellerId) {
        this.itemSellerId = itemSellerId;
    }
    
}

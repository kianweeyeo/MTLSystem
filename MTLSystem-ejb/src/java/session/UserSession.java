package session;

import entity.Cart;
import entity.Item;
import entity.PurchaseOrder;
import entity.MTLUser;
import error.GeneralException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author yeokw | Yeo Kian Wee | A0162262M
 */
@Stateless
public class UserSession implements UserSessionLocal {
    
    @PersistenceContext
    private EntityManager em;
    
    // SYSTEM FUNCTIONS BELOW **************************************************************
    
    // System function
    // Logout will be handled via HttpSession
    @Override
    public MTLUser userLogin(String username, String password) throws GeneralException {
        Query query = em.createQuery("SELECT u FROM MTLUser u WHERE u.username = :username AND u.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        if (!query.getResultList().isEmpty()) {
            MTLUser user = (MTLUser) query.getSingleResult();
            return user;
        } else {
            throw new GeneralException("User Not Found");
        }
    }
    
    // System function
    @Override
    public List<MTLUser> searchUsers(String username) {
        Query q;
        if (username != null) {
            q = em.createQuery("SELECT u FROM MTLUser u WHERE LOWER(u.username) LIKE :username");
            q.setParameter("username", "%" + username.toLowerCase() + "%");
        } else {
            q = em.createQuery("SELECT u FROM MTLUser u");
        }

        return q.getResultList();
    }
    
    // System function
    @Override
    public List<MTLUser> searchUsersByPhoneNumber(int phoneNumber) throws GeneralException {
        Query q;
        if (phoneNumber > 0) {
            q = em.createQuery("SELECT u FROM MTLUser u WHERE u.phoneNumber = :phoneNumber");
            q.setParameter("phoneNumber", phoneNumber);
        } else {
            throw new GeneralException("Invalid Value Entered");
        }

        return q.getResultList();
    }
    
    // System function
    @Override
    public List<MTLUser> searchUsersByEmail(String email) throws GeneralException {
        Query q;
        if (email != null) {
            q = em.createQuery("SELECT u FROM MTLUser u WHERE LOWER(u.email) LIKE :email");
            q.setParameter("email", "%" + email.toLowerCase() + "%");
        } else {
            throw new GeneralException("Invalid Value Entered");
        }

        return q.getResultList();
    }
    
    // System function    
    @Override
    public MTLUser getUser(Long userId) throws GeneralException {
        MTLUser u = em.find(MTLUser.class, userId);

        if (u != null) {
            return u;
        } else {
            throw new GeneralException("User Not Found");
        }
    }
    
    // System function
    @Override
    public void createUser(MTLUser u) {
        em.persist(u);
    }
    
    // System function
    @Override
    public void updateUser(MTLUser u) throws GeneralException {
        MTLUser oldU = em.find(MTLUser.class, u.getUserId());

        if (oldU != null) {
            oldU.setUsername(u.getUsername());
            oldU.setPassword(u.getPassword());
            oldU.setFirstName(u.getFirstName());
            oldU.setLastName(u.getLastName());
            oldU.setPhoneNumber(u.getPhoneNumber());
            oldU.setEmail(u.getEmail());
            oldU.setDateOfBirth(u.getDateOfBirth());
            oldU.setIsSeller(u.isIsSeller());
            oldU.setIsBuyer(u.isIsBuyer());
        } else {
            throw new GeneralException("User Not Found");
        }
    }
    
    // ADMIN FUNCTIONS BELOW **************************************************************
    
    // Admin function - viewAllUsers
    @Override
    public List<MTLUser> viewAllUsers() {
        Query q;
        q = em.createQuery("SELECT u FROM MTLUser u");

        return q.getResultList();
    }
    
    // Admin function - activateUser
    @Override
    public void activateUser(Long userId) throws GeneralException {
        MTLUser u = em.find(MTLUser.class, userId);

        if (u != null) {
            u.setIsActive(true);
        } else {
            throw new GeneralException("User Not Found");
        }
    }
    
    // Admin function - deactivateUser
    @Override
    public void deactivateUser(Long userId) throws GeneralException {
        MTLUser u = em.find(MTLUser.class, userId);

        if (u != null) {
            u.setIsActive(false);
        } else {
            throw new GeneralException("User Not Found");
        }
    }
            
    // Admin function - Only for testing use
    @Override
    public void deleteUser(Long userId) throws GeneralException {
        MTLUser u = em.find(MTLUser.class, userId);
        
        if (u == null){
            throw new GeneralException("User Not Found");
        }
        
        // Manage relationships
        ArrayList<Item> sellerItemList = u.getSellerItemList();
        u.setSellerItemList(null);
        Cart cart = u.getCart();
        u.setCart(null);
        ArrayList<PurchaseOrder> buyerOrderList = u.getBuyerOrderList();
        u.setBuyerOrderList(null);
        
        em.remove(u);
    }
    
    
    // SELLER FUNCTIONS BELOW **************************************************************
    
    
    // Seller function - addSellerItem
    @Override
    public void addSellerItem(Long userId, Item i) throws GeneralException {
        MTLUser u = em.find(MTLUser.class, userId);

        if (i != null) {
            em.persist(i);
            u.getSellerItemList().add(i);
        } else {
            throw new GeneralException("Item Not Found");
        }
    }
    
    // Seller function - List/Search seller’s items:
    // Seller function - listAllItems
    // The system should allow sellers to see their list of items
    @Override
    public List<Item> listAllSellerItems(Long userId) {
        MTLUser u = em.find(MTLUser.class, userId);

        return u.getSellerItemList();
    }
    
    // Seller function - searchItems
    // It should also allow them to search/filter by keywords, etc.
    // Self-defined - Search item by Name, Description, Category, Price.
    @Override
    public List<Item> searchSellerItemsByKeyword(Long userId, Item i) {
        MTLUser u = em.find(MTLUser.class, userId);
        ArrayList<Item> filteredItems = new ArrayList<Item>();

        if (i.getName() != null) {
            for (Item items: u.getSellerItemList()) {
                if (items.getName().contains(i.getName())) {
                    filteredItems.add(items);
                }
            }
        } else if (i.getDescription() != null) {
            for (Item items: u.getSellerItemList()) {
                if (items.getDescription().contains(i.getDescription())) {
                    filteredItems.add(items);
                }
            }
        } else if (i.getCategory() != null) {
            for (Item items: u.getSellerItemList()) {
                if (items.getCategory().contains(i.getCategory())) {
                    filteredItems.add(items);
                }
            }
        } else if (i.getPrice() != -1) {
            for (Item items: u.getSellerItemList()) {
                if (items.getPrice() == i.getPrice()) {
                    filteredItems.add(items);
                }
            }
        } else {
            return new ArrayList<Item>();
        }

        return filteredItems;
    }
    
    // Seller function - deleteSellerItem
    // Seller should not be able to delete an item if it is already associated with an order.
    // To logical delete an item, seller can set the quantity to 0.
    @Override
    public void deleteSellerItem(Long userId, Long itemId) throws GeneralException {
        MTLUser u = em.find(MTLUser.class, userId);
        Item i = em.find(Item.class, itemId);
        
        ArrayList<Item> sellerItemList = u.getSellerItemList();
        
        if (i != null) {
            for (Item item : sellerItemList) {
                if (item.getName() == i.getName()) {
                    if (i.getOrder() != null) {
                        throw new GeneralException("Cannot Delete Item - Already associated with Order");
                    }
                    else {
                        i.setQuantity(0);
                        em.merge(i);
                        em.flush();
                    }
                }
            }
        }
    }
    
    // Seller function - updateItem
    @Override
    public void updateItem(Item i) throws GeneralException {
        Item oldI = em.find(Item.class, i.getItemId());

        if (oldI != null) {
            oldI.setName(i.getName());
            oldI.setDescription(i.getDescription());
            oldI.setQuantity(i.getQuantity());
            oldI.setCategory(i.getCategory());
            oldI.setPrice(i.getPrice());
        } else {
            throw new GeneralException("Item Not Found");
        }
    }
    
    // Seller function - viewAllOrders (Orders for the Seller)
    @Override
    public List<PurchaseOrder> viewAllSellerOrders(Long userId) {
        MTLUser u = em.find(MTLUser.class, userId);
        Query q;
        q = em.createQuery("SELECT o FROM PurchaseOrder o WHERE o.orderSellerId = :userId");
        q.setParameter("userId", userId);        

        return q.getResultList();
    }
    
    // Seller function - updateOrderStatus, need to findOrder then update.
    // Dropdown Values: PAYMENT CONFIRMED, CANCELLED, SHIPPED, DELIVERED
    @Override
    public void updateOrderStatus(PurchaseOrder o) throws GeneralException {
        PurchaseOrder oldO = em.find(PurchaseOrder.class, o.getOrderId());
        
        if (oldO != null) {
            oldO.setOrderStatus(o.getOrderStatus());
        } else {
            throw new GeneralException("Order Not Found");
        }
    }
    
    
    // BUYER FUNCTIONS BELOW **************************************************************
    
    
    // Buyer function - searchItems
    // Users do not need to be logged in in order to search for items,     
    // System should allow users to search by keywords, category, 
    // availability and should display item details (e.g. stock level, etc).
    // Self-defined - Search item by Name, Description, Category, Price.
    public List<Item> searchItems(Item i) {
        Query q;
        if (i.getName() != null) {
            q = em.createQuery("SELECT i FROM Item i WHERE " + "LOWER(i.name) LIKE :name");
            q.setParameter("name", "%" + i.getName().toLowerCase() + "%");
        } else if (i.getDescription() != null) {
            q = em.createQuery("SELECT i FROM Item i WHERE " + "LOWER(i.description) LIKE :description");
            q.setParameter("description", "%" + i.getDescription().toLowerCase() + "%");
        } else if (i.getCategory() != null) {
            q = em.createQuery("SELECT i FROM Item i WHERE " + "LOWER(i.category) LIKE :category");
            q.setParameter("category", "%" + i.getCategory().toLowerCase() + "%");
        } else if (i.getPrice() != 0) {
            q = em.createQuery("SELECT i FROM Item i WHERE i.price = :price");
            q.setParameter("price", i.getPrice());
        } else {
            return new ArrayList<Item>();
        }
        
        return q.getResultList();
    }
        
    // Buyer function - addItemToCart
    // User has to be logged in before they can add something to the cart.
    // Make cart page and cart button verify user session first
    public void addItemToCart(Long userId, Item i) throws GeneralException {
        MTLUser u = em.find(MTLUser.class, userId);
        
        if (u.getCart() == null) {
            u.setCart(new Cart());
        }

        if (i != null) {
            em.persist(i);
            u.getCart().getCartItemList().add(i);
        } else {
            throw new GeneralException("Item Not Found");
        }
    }    
    
    // Buyer function - checkOutCart - Transfer items in Cart to a new Order
    // While you should take in credit card details, you do not need to integrate with payment gateway and can assume that the payment will always be successful.
    @Override
    public void checkOutCart(Long userId, Cart c) throws GeneralException {
        MTLUser u = em.find(MTLUser.class, userId);        

        if (c != null) {
            if (c.getCreditCardNum() == 0 
                    || c.getCreditCardExpiry() == null 
                    || c.getCreditCardCvv() == 0) {
                throw new GeneralException("Credit Card Details Not Complete");
            } else {
                PurchaseOrder o = new PurchaseOrder();
                o.setOrderStatus("PAYMENT CONFIRMED");
                o.setOrderCreated(new Date());
                o.setOrderSellerId(c.getCartItemList().get(0).getItemSellerId());
                o.setOrderItemList(c.getCartItemList());
                em.persist(o);
                u.getBuyerOrderList().add(o);
            }
        } else {
            throw new GeneralException("Cart Not Found");
        }
    }

    // Buyer function - addFeedback
    // After an order has been fulfilled, buyers should be able to leave a feedback (review + rating).
    // They should only be allowed to do this once for each fulfilled order.
    @Override
    public void addFeedback(Long orderId, PurchaseOrder o) throws GeneralException {
        PurchaseOrder oldO = em.find(PurchaseOrder.class, orderId);

        if (oldO != null) {
            if (oldO.getFeedbackReview() != null) {
                oldO.setFeedbackReview(o.getFeedbackReview());
                oldO.setFeedbackRating(o.getFeedbackRating());
            }
        } else {
            throw new GeneralException("Feedback Already Exists");
        }
    }
    
    // Buyer function - viewAllOrders (Orders by the Buyer)
    @Override
    public List<PurchaseOrder> viewAllBuyerOrders(Long userId) {
        MTLUser u = em.find(MTLUser.class, userId);

        return u.getBuyerOrderList();
    }
}

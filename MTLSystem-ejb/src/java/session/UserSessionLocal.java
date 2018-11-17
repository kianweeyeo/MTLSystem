package session;

import entity.Item;
import entity.PurchaseOrder;
import entity.MTLUser;
import entity.Cart;
import error.GeneralException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yeokw | Yeo Kian Wee | A0162262M
 */
@Local
public interface UserSessionLocal {
    
    // System Functions
    public MTLUser userLogin(String username, String password);
    public List<MTLUser> searchUsers(String username);
    public MTLUser getUser(Long userId) throws GeneralException;
    public void createUser(MTLUser u);
    public void updateUser(MTLUser u) throws GeneralException;
    
    // Admin Functions
    public List<MTLUser> viewAllUsers();
    public void activateUser(Long userId) throws GeneralException;
    public void deactivateUser(Long userId) throws GeneralException;
    public void deleteUser(Long userId) throws GeneralException;

    // Seller Functions
    public void addSellerItem(Long userId, Item i) throws GeneralException;
    public List<Item> listAllSellerItems(Long userId);
    public List<Item> searchSellerItemsByKeyword(Long userId, Item i);
    public void deleteSellerItem(Long userId, Long itemId) throws GeneralException;
    public void updateItem(Item i) throws GeneralException;
    public List<PurchaseOrder> viewAllSellerOrders(Long userId);
    public void updateOrderStatus(PurchaseOrder o) throws GeneralException;
    
    // Buyer Functions
    public List<Item> searchItems(Item i);
    public void addItemToCart(Long userId, Item i) throws GeneralException;
    public void checkOutCart(Long userId, Cart c) throws GeneralException;
    public void addFeedback(Long orderId, PurchaseOrder o) throws GeneralException;
    public List<PurchaseOrder> viewAllBuyerOrders(Long userId);
}

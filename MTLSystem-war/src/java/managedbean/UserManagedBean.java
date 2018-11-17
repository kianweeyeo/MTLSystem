package managedbean;

import entity.Cart;
import entity.Item;
import entity.MTLUser;
import entity.PurchaseOrder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import session.UserSessionLocal;

/**
 *
 * @author yeokw | Yeo Kian Wee | A0162262M
 */
@ManagedBean
@ViewScoped
public class UserManagedBean {
    
    @EJB
    private UserSessionLocal userSessionLocal;
    
    private Long userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String email;
    private Date dateOfBirth;
    private boolean isAdmin;
    private boolean isSeller;
    private boolean isBuyer;
    private boolean isActive;
    private Date userCreated;

    private ArrayList<Item> sellerItemList;
    private Cart cart;
    private ArrayList<PurchaseOrder> buyerOrderList;
    
    // Seller attributes
    private String itemName;
    private String itemDescription;
    private int itemQuantity;
    private String itemCategory;
    private double itemPrice;
    
    //used by editUser.xhtml
    private List<MTLUser> users;
    private MTLUser selectedUser;
    private List<Item> items;

    public UserManagedBean() {
    }
    
    @PostConstruct
    public void init() {
        setUsers(userSessionLocal.searchUsers(null));
    } //end init
    
    public void createUser(ActionEvent evt) {
        MTLUser u = new MTLUser();
        u.setUsername(username);
        u.setPassword(password);
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setPhoneNumber(phoneNumber);
        u.setEmail(email);
        u.setDateOfBirth(dateOfBirth);
        u.setIsAdmin(false);
        u.setIsSeller(isSeller);
        u.setIsBuyer(isBuyer);

        userSessionLocal.createUser(u);
    }
    
    public void updateUser(ActionEvent evt) {
        FacesContext context = FacesContext.getCurrentInstance();
        getSelectedUser().setUsername(username);
        getSelectedUser().setPassword(password);
        getSelectedUser().setFirstName(firstName);
        getSelectedUser().setLastName(lastName);
        getSelectedUser().setPhoneNumber(phoneNumber);
        getSelectedUser().setEmail(email);
        getSelectedUser().setDateOfBirth(dateOfBirth);
        getSelectedUser().setIsSeller(isSeller);
        getSelectedUser().setIsBuyer(isBuyer);
        try {
            userSessionLocal.updateUser(getSelectedUser());
        } catch (Exception e) {
            //show with an error icon
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to update User"));
            return;
        }
        //need to make sure reinitialize the users collection
        context.addMessage(null, new FacesMessage("Success", "Successfully updated User"));
    } //end updateUser
    
    public void loadSelectedUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.setSelectedUser(userSessionLocal.getUser(userId));
            username = this.getSelectedUser().getUsername();
            password = "";
            firstName = this.getSelectedUser().getFirstName();
            lastName = this.getSelectedUser().getLastName();
            phoneNumber = this.getSelectedUser().getPhoneNumber();
            email = this.getSelectedUser().getEmail();
            dateOfBirth = this.getSelectedUser().getDateOfBirth();
            isSeller = this.getSelectedUser().isIsSeller();
            isBuyer = this.getSelectedUser().isIsBuyer();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to load User"));
        }
    } //end loadSelectedUser
    
    public void activateUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String userIdStr = params.get("userId");
        Long userId = Long.parseLong(userIdStr);
        try {
            userSessionLocal.activateUser(userId);
        } catch (Exception e) {
        //show with an error icon
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to activate User"));
            return;
        }
        context.addMessage(null, new FacesMessage("Success", "Successfully activated User"));
        init();
    } //end activateUser
    
    public void deactivateUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String userIdStr = params.get("userId");
        Long userId = Long.parseLong(userIdStr);
        try {
            userSessionLocal.deactivateUser(userId);
        } catch (Exception e) {
        //show with an error icon
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to deactivate User"));
            return;
        }
        context.addMessage(null, new FacesMessage("Success", "Successfully deactivated User"));
        init();
    } //end deactivateUser
    
    public void addItem(ActionEvent evt) {
        FacesContext context = FacesContext.getCurrentInstance();
        Item i = new Item();
        i.setName(itemName);
        i.setDescription(itemDescription);
        i.setQuantity(itemQuantity);
        i.setCategory(itemCategory);
        i.setPrice(itemPrice);
        i.setItemCreated(new Date());
        i.setItemSellerId(getSelectedUser().getUserId());
        
        try {
            userSessionLocal.addSellerItem(getSelectedUser().getUserId(), i);
        } catch (Exception e) {
        //show with an error icon
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to add Item"));
            return;
        }
        context.addMessage(null, new FacesMessage("Success", "Successfully added Item"));
        init();
    }
    
    //new method to "combine" both add and update
    public void submitAction(ActionEvent evt) {
        if (this.getSelectedUser() != null) {
            this.updateUser(evt);
        } else {
            this.createUser(evt);
        }
    } //end submitAction

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

    /**
     * @return the users
     */
    public List<MTLUser> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<MTLUser> users) {
        this.users = users;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the itemDescription
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * @param itemDescription the itemDescription to set
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * @return the itemQuantity
     */
    public int getItemQuantity() {
        return itemQuantity;
    }

    /**
     * @param itemQuantity the itemQuantity to set
     */
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    /**
     * @return the itemCategory
     */
    public String getItemCategory() {
        return itemCategory;
    }

    /**
     * @param itemCategory the itemCategory to set
     */
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    /**
     * @return the itemPrice
     */
    public double getItemPrice() {
        return itemPrice;
    }

    /**
     * @param itemPrice the itemPrice to set
     */
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * @return the items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * @return the phoneNumber
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the selectedUser
     */
    public MTLUser getSelectedUser() {
        return selectedUser;
    }

    /**
     * @param selectedUser the selectedUser to set
     */
    public void setSelectedUser(MTLUser selectedUser) {
        this.selectedUser = selectedUser;
    }
    
}

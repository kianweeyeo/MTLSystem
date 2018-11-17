package entity;

import entity.Cart;
import entity.Item;
import entity.PurchaseOrder;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-31T11:07:05")
@StaticMetamodel(MTLUser.class)
public class MTLUser_ { 

    public static volatile SingularAttribute<MTLUser, String> lastName;
    public static volatile SingularAttribute<MTLUser, Boolean> isSeller;
    public static volatile ListAttribute<MTLUser, Item> sellerItemList;
    public static volatile SingularAttribute<MTLUser, Date> dateOfBirth;
    public static volatile SingularAttribute<MTLUser, Boolean> isAdmin;
    public static volatile SingularAttribute<MTLUser, Boolean> isActive;
    public static volatile SingularAttribute<MTLUser, Long> userId;
    public static volatile SingularAttribute<MTLUser, Boolean> isBuyer;
    public static volatile SingularAttribute<MTLUser, Date> userCreated;
    public static volatile SingularAttribute<MTLUser, Cart> cart;
    public static volatile SingularAttribute<MTLUser, String> firstName;
    public static volatile SingularAttribute<MTLUser, String> password;
    public static volatile SingularAttribute<MTLUser, String> username;
    public static volatile ListAttribute<MTLUser, PurchaseOrder> buyerOrderList;

}
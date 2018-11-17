package entity;

import entity.Item;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-18T00:26:28")
@StaticMetamodel(Cart.class)
public class Cart_ { 

    public static volatile SingularAttribute<Cart, Boolean> paymentSuccess;
    public static volatile SingularAttribute<Cart, Integer> creditCardCvv;
    public static volatile SingularAttribute<Cart, Integer> creditCardNum;
    public static volatile ListAttribute<Cart, Item> cartItemList;
    public static volatile SingularAttribute<Cart, Long> cartId;
    public static volatile SingularAttribute<Cart, Date> creditCardExpiry;

}
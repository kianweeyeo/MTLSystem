package entity;

import entity.PurchaseOrder;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-18T00:26:28")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, Long> itemId;
    public static volatile SingularAttribute<Item, Integer> quantity;
    public static volatile SingularAttribute<Item, Long> itemSellerId;
    public static volatile SingularAttribute<Item, Double> price;
    public static volatile SingularAttribute<Item, String> name;
    public static volatile SingularAttribute<Item, String> description;
    public static volatile SingularAttribute<Item, String> category;
    public static volatile SingularAttribute<Item, Date> itemCreated;
    public static volatile SingularAttribute<Item, PurchaseOrder> order;

}
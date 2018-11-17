package entity;

import entity.Item;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-31T11:07:05")
@StaticMetamodel(PurchaseOrder.class)
public class PurchaseOrder_ { 

    public static volatile SingularAttribute<PurchaseOrder, Long> orderSellerId;
    public static volatile SingularAttribute<PurchaseOrder, Date> orderCreated;
    public static volatile SingularAttribute<PurchaseOrder, Integer> feedbackRating;
    public static volatile SingularAttribute<PurchaseOrder, Long> orderId;
    public static volatile ListAttribute<PurchaseOrder, Item> orderItemList;
    public static volatile SingularAttribute<PurchaseOrder, String> feedbackReview;
    public static volatile SingularAttribute<PurchaseOrder, String> orderStatus;

}
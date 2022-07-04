package dto;

import entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/

public class OrderDTO {
    private String oid;
    private Date date;
    private String customerId;
    private ArrayList<OrderDetail> orderDetailsArrayList;

    public OrderDTO(String orderId, String orderDate, String customer, ArrayList<OrderDetail> orderDetails) {
    }

    public OrderDTO(String oid, Date date, String customerId, ArrayList<OrderDetail> orderDetailsArrayList) {
        this.oid = oid;
        this.date = date;
        this.customerId = customerId;
        this.orderDetailsArrayList = orderDetailsArrayList;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public ArrayList<OrderDetail> getOrderDetailsArrayList() {
        return orderDetailsArrayList;
    }

    public void setOrderDetailsArrayList(ArrayList<OrderDetail> orderDetailsArrayList) {
        this.orderDetailsArrayList = orderDetailsArrayList;
    }
}

package dao.custom;

import dao.CrudDAO;
import entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/
public interface OrderDetailsDAO extends CrudDAO<OrderDetail,String> {
    boolean saveOrderDetails(String id, ArrayList<OrderDetail> dtos) throws SQLException;
}

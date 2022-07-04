package dao.custom;

import dao.CrudDAO;
import entity.Item;

import javax.json.JsonArrayBuilder;
import java.sql.SQLException;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/
public interface ItemDAO extends CrudDAO<Item,String> {
    boolean updateQty(String id, int qty) throws SQLException;

    JsonArrayBuilder itemDetailsForOrder(String id) throws SQLException;

}

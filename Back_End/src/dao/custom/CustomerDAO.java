package dao.custom;

import dao.CrudDAO;
import entity.Customer;

import javax.json.JsonArrayBuilder;
import java.sql.SQLException;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/
public interface CustomerDAO extends CrudDAO<Customer,String> {
    JsonArrayBuilder cusIdForOrder(String id) throws SQLException;
}

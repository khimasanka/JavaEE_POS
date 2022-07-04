package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.SQLException;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/
public interface CustomerBO extends SuperBO {
    JsonArrayBuilder getAllCustomer() throws SQLException, ClassNotFoundException;

    JsonObjectBuilder generateCustomerID() throws SQLException;

    JsonArrayBuilder searchCustomer(String id) throws SQLException;

    boolean addCustomer(CustomerDTO customerDTO) throws SQLException;

    boolean deleteCustomer(String id) throws SQLException;

    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException;

    JsonArrayBuilder loadAllCusIDs() throws SQLException;

    JsonArrayBuilder cusIdForOrder(String id) throws SQLException;

}

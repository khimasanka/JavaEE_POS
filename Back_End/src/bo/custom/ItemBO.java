package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.SQLException;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/
public interface ItemBO extends SuperBO {
    JsonArrayBuilder getAllItems() throws SQLException;

    JsonObjectBuilder generateItemID() throws SQLException;

    JsonArrayBuilder searchItem(String id) throws SQLException;

    boolean saveItem(ItemDTO itemDTO) throws SQLException;

    boolean deleteItem(String id) throws SQLException;

    boolean updateItem(ItemDTO itemDTO) throws SQLException;

    JsonArrayBuilder loadAllItemIDs() throws SQLException;

    JsonArrayBuilder ItemDetailsForOrder(String id) throws SQLException;
}

package bo.custom;

import bo.SuperBO;
import dto.OrderDTO;

import javax.json.JsonObjectBuilder;
import java.sql.SQLException;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/
public interface OrderBO extends SuperBO {
    boolean saveOrder(OrderDTO orderDTO) throws SQLException;

    JsonObjectBuilder generateID() throws SQLException;

}

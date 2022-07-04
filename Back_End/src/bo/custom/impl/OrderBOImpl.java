package bo.custom.impl;

import bo.custom.OrderBO;
import dao.DAOFactory;
import dao.custom.impl.OrderDAOImpl;
import dao.custom.impl.OrderDetailsDAOImpl;
import dto.OrderDTO;
import entity.Order;
import servlet.OrderServlet;

import javax.json.JsonObjectBuilder;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/
public class OrderBOImpl implements OrderBO {
    OrderDAOImpl orderDAO = (OrderDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailsDAOImpl orderDetailsDAO = (OrderDetailsDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    @Override
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException {
        Connection connection = null;
        try {
            connection = OrderServlet.ds.getConnection();
            connection.setAutoCommit(false);

            if (orderDAO.save(new Order(orderDTO.getOid(), orderDTO.getDate(), orderDTO.getCustomerId()))) {
                if (orderDetailsDAO.saveOrderDetails(orderDTO.getOid(), orderDTO.getOrderDetailsArrayList())) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public JsonObjectBuilder generateID() throws SQLException {
        return orderDAO.generateID();
    }
}

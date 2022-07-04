package dao.custom.impl;

import dao.DAOFactory;
import dao.custom.ItemDAO;
import dao.custom.OrderDetailsDAO;
import entity.OrderDetail;
import servlet.OrderServlet;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/
public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public JsonArrayBuilder getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public JsonObjectBuilder generateID() throws SQLException {
        return null;
    }

    @Override
    public JsonArrayBuilder search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean save(OrderDetail orderDetail) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(OrderDetail orderDetail) throws SQLException {
        return false;
    }

    @Override
    public boolean saveOrderDetails(String id, ArrayList<OrderDetail> dtos) throws SQLException {
        Connection connection = OrderServlet.ds.getConnection();
        for (OrderDetail items : dtos) {
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO orderdetails VALUES(?,?,?,?,?)");
            pstm.setObject(1, items.getId());
            pstm.setObject(2, items.getItemCode());
            pstm.setObject(3, items.getQty());
            pstm.setObject(4, items.getUnitPrice());
            if (pstm.executeUpdate() > 0) {
                if (itemDAO.updateQty(items.getItemCode(), items.getQty())) {
                } else {
                    connection.close();
                    return false;
                }
            } else {
                connection.close();
                return false;
            }
        }
        connection.close();
        return true;
    }
}

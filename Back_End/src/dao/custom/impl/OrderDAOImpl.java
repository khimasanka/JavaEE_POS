package dao.custom.impl;

import dao.custom.OrderDAO;
import entity.Order;
import servlet.OrderServlet;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/
public class OrderDAOImpl implements OrderDAO {
    @Override
    public JsonArrayBuilder getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public JsonObjectBuilder generateID() throws SQLException {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        Connection conn = OrderServlet.ds.getConnection();
        ResultSet rst = conn.prepareStatement("SELECT oid FROM orders ORDER BY oid DESC LIMIT 1").executeQuery();
        if (rst.next()) {
            int tempId = Integer.parseInt(rst.getString(1).split("O")[1]);
            tempId += 1;
            if (tempId < 10) {
                objectBuilder.add("oId", "O00" + tempId);
            } else if (tempId < 100) {
                objectBuilder.add("oId", "O0" + tempId);
            } else if (tempId < 1000) {
                objectBuilder.add("oId", "O-" + tempId);
            }
        } else {
            objectBuilder.add("oId", "O001");
        }
        conn.close();
        return objectBuilder;
    }

    @Override
    public JsonArrayBuilder search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean save(Order order) throws SQLException {
        Connection conn = OrderServlet.ds.getConnection();
        PreparedStatement pstm = conn.prepareStatement("INSERT INTO orders VALUES(?,?,?)");
        pstm.setObject(1, order.getOid());
        pstm.setObject(2, order.getDate());
        pstm.setObject(3, order.getCustomerId());

        boolean b = pstm.executeUpdate() > 0;
        conn.close();
        return b;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Order order) throws SQLException {
        return false;
    }
}

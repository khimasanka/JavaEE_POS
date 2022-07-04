package dao.custom.impl;

import dao.custom.ItemDAO;
import entity.Item;
import servlet.ItemServlet;
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
public class ItemDAOImpl implements ItemDAO {
    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
    @Override
    public JsonArrayBuilder getAll() throws SQLException, ClassNotFoundException {
       return null;
    }

    @Override
    public JsonObjectBuilder generateID() throws SQLException {
        Connection conn = ItemServlet.ds.getConnection();
        ResultSet rstI = conn.prepareStatement("SELECT code FROM item ORDER BY code DESC LIMIT 1").executeQuery();
        if (rstI.next()) {
            int tempId = Integer.parseInt(rstI.getString(1).split("I")[1]);
            tempId += 1;
            if (tempId < 10) {
                objectBuilder.add("code", "I00" + tempId);
            } else if (tempId < 100) {
                objectBuilder.add("code", "I0" + tempId);
            } else if (tempId < 1000) {
                objectBuilder.add("code", "I-" + tempId);
            }
        } else {
            objectBuilder.add("code", "I001");
        }
        conn.close();
        return objectBuilder;
    }

    @Override
    public JsonArrayBuilder search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean save(Item item) throws SQLException {
        Connection conn = ItemServlet.ds.getConnection();
        PreparedStatement pstm = conn.prepareStatement("INSERT INTO item VALUE(?,?,?,?)");
        pstm.setObject(1, item.getCode());
        pstm.setObject(2, item.getDescription());
        pstm.setObject(3, item.getQtyOnHand());
        pstm.setObject(4, item.getUnitPrice());
        boolean b = pstm.executeUpdate() > 0;
        conn.close();
        return b;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Connection conn = ItemServlet.ds.getConnection();
        PreparedStatement pstm = conn.prepareStatement("DELETE FROM item WHERE code=?");
        pstm.setObject(1, id);
        boolean b = pstm.executeUpdate() > 0;
        conn.close();
        return b;
    }

    @Override
    public boolean update(Item item) throws SQLException {
        Connection conn = ItemServlet.ds.getConnection();
        PreparedStatement pstm = conn.prepareStatement("UPDATE item SET description=?, qtyOnHand=?, unitPrice=? WHERE code=?");
        pstm.setObject(1, item.getDescription());
        pstm.setObject(2, item.getQtyOnHand());
        pstm.setObject(3, item.getUnitPrice());
        pstm.setObject(4, item.getCode());
        boolean b = pstm.executeUpdate() > 0;
        conn.close();
        return b;
    }

    @Override
    public boolean updateQty(String id, int qty) throws SQLException {
        Connection connection = OrderServlet.ds.getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE item SET qtyOnHand=(qtyOnHand-?) WHERE code=?");
        pstm.setObject(1, qty);
        pstm.setObject(2, id);
        boolean b = pstm.executeUpdate() > 0;
        connection.close();
        return b;
    }

    @Override
    public JsonArrayBuilder itemDetailsForOrder(String id) throws SQLException {
        Connection conn = OrderServlet.ds.getConnection();
        PreparedStatement pstm = conn.prepareStatement("SELECT * FROM item WHERE code=?");
        pstm.setObject(1, id);
        ResultSet rst = pstm.executeQuery();
        if (rst.next()) {
            String description = rst.getString(2);
            String qtyOnHand = rst.getString(3);
            String unitPrice = rst.getString(4);

            objectBuilder.add("description", description);
            objectBuilder.add("qtyOnHand", qtyOnHand);
            objectBuilder.add("unitPrice", unitPrice);
            arrayBuilder.add(objectBuilder.build());
        }
        conn.close();
        return arrayBuilder;
    }
}

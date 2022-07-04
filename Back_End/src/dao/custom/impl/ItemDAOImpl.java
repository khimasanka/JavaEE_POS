package dao.custom.impl;

import dao.custom.ItemDAO;
import entity.Item;
import servlet.ItemServlet;

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
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        Connection conn = ItemServlet.ds.getConnection();
        ResultSet rst = conn.prepareStatement("SELECT * FROM item").executeQuery();
        while (rst.next()) {
            String itemCode = rst.getString(1);
            String itemName = rst.getString(2);
            int itemQtyOnHand = rst.getInt(3);
            int itemUnitPrice = rst.getInt(4);

            objectBuilder.add("code", itemCode);
            objectBuilder.add("name", itemName);
            objectBuilder.add("qtyOnHand", itemQtyOnHand);
            objectBuilder.add("unitPrice", itemUnitPrice);

            arrayBuilder.add(objectBuilder.build());
        }
        conn.close();
        return arrayBuilder;
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
    public boolean save(Item item) throws SQLException {
       return true;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Item item) throws SQLException {
        return false;
    }

}

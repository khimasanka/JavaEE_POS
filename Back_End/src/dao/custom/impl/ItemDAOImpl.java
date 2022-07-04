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

package bo.custom.impl;

import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.impl.ItemDAOImpl;
import dto.ItemDTO;
import entity.Item;
import lombok.SneakyThrows;
import servlet.ItemServlet;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/
public class ItemBOImpl implements ItemBO {
    ItemDAOImpl itemDAO = (ItemDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);


    @Override
    public JsonArrayBuilder getAllItems() throws SQLException {
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
    public JsonObjectBuilder generateItemID() throws SQLException {
        return itemDAO.generateID();
    }

    @Override
    public JsonArrayBuilder searchItem(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException {
        Item item = new Item(itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getQtyOnHand(), itemDTO.getUnitPrice());
        return itemDAO.save(item);
    }

    @Override
    public boolean deleteItem(String id) throws SQLException {
        return itemDAO.delete(id);
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException {
        Item item = new Item(itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getQtyOnHand(), itemDTO.getUnitPrice());
        return itemDAO.update(item);
    }

    @Override
    public JsonArrayBuilder loadAllItemIDs() throws SQLException {
        return null;
    }

    @Override
    public JsonArrayBuilder ItemDetailsForOrder(String id) throws SQLException {
        return itemDAO.itemDetailsForOrder(id);
    }
}

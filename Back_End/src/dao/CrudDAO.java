package dao;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.SQLException;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/
public interface CrudDAO <a, id> extends SuperDAO{
    JsonArrayBuilder getAll() throws SQLException, ClassNotFoundException;

    JsonObjectBuilder generateID() throws SQLException;

    JsonArrayBuilder search(String id) throws SQLException;

    boolean save(a a) throws SQLException;

    boolean delete(String id) throws SQLException;

    boolean update(a a) throws SQLException;
}

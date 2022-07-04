package dao.custom.impl;

import dao.custom.CustomerDAO;
import entity.Customer;
import servlet.CustomerServlet;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.*;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/
public class CustomerDAOImpl implements CustomerDAO {

    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

    @Override
    public JsonArrayBuilder getAll() throws SQLException, ClassNotFoundException {
        Connection conn = CustomerServlet.ds.getConnection();
        /*Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","1234");

       */ ResultSet rst = conn.prepareStatement("SELECT * FROM customer").executeQuery();
        while (rst.next()) {
            String cusID = rst.getString(1);
            String cusName = rst.getString(2);
            String cusAddress = rst.getString(3);
            int cusSalary = rst.getInt(4);

            objectBuilder.add("id", cusID);
            objectBuilder.add("name", cusName);
            objectBuilder.add("address", cusAddress);
            objectBuilder.add("salary", cusSalary);

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
    public boolean save(Customer customer) throws SQLException {
        Connection con = CustomerServlet.ds.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement("INSERT into customer values (?,?,?,?)");
        preparedStatement.setObject(1,customer.getId());
        preparedStatement.setObject(2,customer.getName());
        preparedStatement.setObject(2,customer.getAddress());
        preparedStatement.setObject(4,customer.getSalary());
        boolean b =preparedStatement.executeUpdate()>0;
        con.close();
        return b;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        return false;
    }
}

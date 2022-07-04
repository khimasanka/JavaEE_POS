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
        Connection conn = CustomerServlet.ds.getConnection();
        ResultSet rstI = conn.prepareStatement("SELECT id FROM customer ORDER BY id DESC LIMIT 1").executeQuery();
        if (rstI.next()) {
            int tempId = Integer.parseInt(rstI.getString(1).split("C")[1]);
            tempId += 1;
            if (tempId < 10) {
                objectBuilder.add("id", "C00" + tempId);
            } else if (tempId < 100) {
                objectBuilder.add("id", "C0" + tempId);
            } else if (tempId < 1000) {
                objectBuilder.add("id", "C" + tempId);
            }
        } else {
            objectBuilder.add("id", "C001");
        }
        conn.close();
        return objectBuilder;
    }

    @Override
    public JsonArrayBuilder search(String id) throws SQLException {
        Connection conn = CustomerServlet.ds.getConnection();
        PreparedStatement pstm = conn.prepareStatement("SELECT * FROM customer WHERE id=?");
        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String cusIDS = resultSet.getString(1);
            String cusNameS = resultSet.getString(2);
            String cusAddressS = resultSet.getString(3);
            int cusSalaryS = resultSet.getInt(4);

            objectBuilder.add("id", cusIDS);
            objectBuilder.add("name", cusNameS);
            objectBuilder.add("address", cusAddressS);
            objectBuilder.add("salary", cusSalaryS);

            arrayBuilder.add(objectBuilder.build());

            System.out.println(cusIDS + " " + cusNameS + " " + cusAddressS + " " + cusSalaryS);
        }
        conn.close();
        return arrayBuilder;
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
        Connection con = CustomerServlet.ds.getConnection();
        PreparedStatement pstm = con.prepareStatement("DELETE FROM customer WHERE id=?");
        pstm.setObject(1, id);
        boolean b = pstm.executeUpdate() > 0;
        con.close();
        return b;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        Connection con = CustomerServlet.ds.getConnection();
        PreparedStatement pstm = con.prepareStatement("UPDATE customer SET name=?, address=?, salary=? WHERE id=?");
        pstm.setObject(1, customer.getName());
        pstm.setObject(2, customer.getAddress());
        pstm.setObject(3, customer.getSalary());
        pstm.setObject(4, customer.getId());
        boolean b = pstm.executeUpdate() > 0;
        con.close();
        return b;
    }
}

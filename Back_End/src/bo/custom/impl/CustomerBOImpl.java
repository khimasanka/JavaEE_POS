package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.impl.CustomerDAOImpl;
import dto.CustomerDTO;
import entity.Customer;
import servlet.CustomerServlet;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/
public class CustomerBOImpl implements CustomerBO {
    CustomerDAOImpl customerDAO = (CustomerDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public JsonArrayBuilder getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    @Override
    public JsonObjectBuilder generateCustomerID() throws SQLException {
        return null;
    }

    @Override
    public JsonArrayBuilder searchCustomer(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws SQLException {
        Customer customer = new Customer(customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getSalary());
        return customerDAO.save(customer);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return customerDAO.delete(id);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException {
        return false;
    }

    @Override
    public JsonArrayBuilder loadAllCusIDs() throws SQLException {
        return null;
    }
}

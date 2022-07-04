package servlet;

import bo.BOFactory;
import bo.custom.impl.CustomerBOImpl;
import dto.CustomerDTO;

import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/
@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    @Resource(name = "java:comp/env/jdbc/pool")
    public static DataSource ds;

    CustomerBOImpl customerBO = (CustomerBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.CUSTOMER);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       /* try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","1234");
            ResultSet resultSet = connection.prepareStatement("SELECT * from customer").executeQuery();
            while (resultSet.next()){
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                double salary = resultSet.getDouble(4);

                System.out.println(id+name+address+salary);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/

        resp.setContentType("application/json");
        JsonObjectBuilder dataMsgBuilder = Json.createObjectBuilder();
        PrintWriter writer = resp.getWriter();

        try {
            String option = req.getParameter("option");
            switch (option) {
                case "GENERATE":
                    dataMsgBuilder.add("data", customerBO.generateCustomerID());
                    dataMsgBuilder.add("message", "Done");
                    dataMsgBuilder.add("status", 200);
                    writer.print(dataMsgBuilder.build());
                    resp.addHeader("Access-Control-Allow-Origin","*");
                    break;

                case "GETALL":
                    resp.setStatus(HttpServletResponse.SC_OK);//201
                    dataMsgBuilder.add("data", customerBO.getAllCustomer());
                    dataMsgBuilder.add("message", "Done");
                    dataMsgBuilder.add("status", 200);
                    writer.print(dataMsgBuilder.build());
                    //resp.addHeader("Access-Control-Allow-Origin","*");
                    break;
                case "SEARCH":
                    String id = req.getParameter("id");
                    resp.setStatus(HttpServletResponse.SC_OK);//201
                    dataMsgBuilder.add("data", customerBO.searchCustomer(id));
                    dataMsgBuilder.add("message", "Done");
                    dataMsgBuilder.add("status", 200);
                    writer.print(dataMsgBuilder.build());
                    break;
            }

            System.out.println("success");

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_OK); //200
            dataMsgBuilder.add("status", 400);
            dataMsgBuilder.add("message", "Error");
            dataMsgBuilder.add("data", e.getLocalizedMessage());
            writer.print(dataMsgBuilder.build());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* resp.setContentType("application/json");

        String cusID = req.getParameter("cusID");
        String cusName = req.getParameter("cusName");
        String cusAddress = req.getParameter("cusAddress");
        String cusSalary = req.getParameter("cusSalary");
        CustomerDTO customerDTO = new CustomerDTO(cusID,
                cusName,
                cusAddress,
                Double.parseDouble(cusSalary));
        PrintWriter writer = resp.getWriter();
        JsonObjectBuilder response = Json.createObjectBuilder();

        try {
            if (customerBO.addCustomer(customerDTO)) {
                resp.setStatus(201);
                response.add("status", 200);
                response.add("message", "Successfully Added");
                response.add("data", "");
                writer.print(response.build());

            }

        } catch (SQLException e) {
            resp.setStatus(200);
            response.add("status", 400);
            response.add("message", "Add Error");
            response.add("data", e.getLocalizedMessage());
            writer.print(response.build());
        }
*/
        String customerID = req.getParameter("cusID");
        String customerName = req.getParameter("cusName");
        String customerAddress = req.getParameter("cusAddress");
        String salary = req.getParameter("cusSalary ");

        System.out.println(customerID+customerAddress);

//        resp.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        try {
            Connection connection = ds.getConnection();
            PreparedStatement pstm = connection.prepareStatement("Insert into Customer values(?,?,?,?)");
            pstm.setObject(1, customerID);
            pstm.setObject(2, customerName);
            pstm.setObject(3, customerAddress);
            pstm.setObject(4, salary);

            if (pstm.executeUpdate() > 0) {
                JsonObjectBuilder response = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_CREATED);//201
                response.add("status", 200);
                response.add("message", "Successfully Added");
                response.add("data", "");
                writer.print(response.build());
            }
            connection.close();
        } catch (SQLException throwables) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", throwables.getLocalizedMessage());
            writer.print(response.build());
            resp.setStatus(HttpServletResponse.SC_OK); //200
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String customerID = req.getParameter("customerID");
        JsonObjectBuilder dataMsgBuilder = Json.createObjectBuilder();
        PrintWriter writer = resp.getWriter();

        try {
            if (customerBO.deleteCustomer(customerID)) {
                resp.setStatus(HttpServletResponse.SC_OK); //200
                dataMsgBuilder.add("data", "");
                dataMsgBuilder.add("message", "Customer Deleted");
                dataMsgBuilder.add("status", 200);
                writer.print(dataMsgBuilder.build());
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_OK); //200
            dataMsgBuilder.add("status", 400);
            dataMsgBuilder.add("message", "Error");
            dataMsgBuilder.add("data", e.getLocalizedMessage());
            writer.print(dataMsgBuilder.build());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String cusIDUpdate = jsonObject.getString("id");
        String cusNameUpdate = jsonObject.getString("name");
        String cusAddressUpdate = jsonObject.getString("address");
        String cusSalaryUpdate = jsonObject.getString("salary");
        CustomerDTO customerDTO = new CustomerDTO(cusIDUpdate, cusNameUpdate, cusAddressUpdate, Integer.parseInt(cusSalaryUpdate));
        PrintWriter writer = resp.getWriter();
        JsonObjectBuilder response = Json.createObjectBuilder();
        try {
            if (customerBO.updateCustomer(customerDTO)) {
                resp.setStatus(HttpServletResponse.SC_CREATED);//201
                response.add("status", 200);
                response.add("message", "Successfully Updated");
                response.add("data", "");
                writer.print(response.build());
            }

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_OK); //200
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", e.getLocalizedMessage());
            writer.print(response.build());
        }
    }
}

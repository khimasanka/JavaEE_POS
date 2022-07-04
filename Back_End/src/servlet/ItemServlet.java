package servlet;

import bo.BOFactory;
import bo.custom.impl.ItemBOImpl;

import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    public static DataSource ds;
    ItemBOImpl itemBO = (ItemBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.ITEM);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        JsonObjectBuilder dataMsgBuilder = Json.createObjectBuilder();
        PrintWriter writer = resp.getWriter();

        try {
            String option = req.getParameter("option");
            switch (option) {
                case "GENERATED_ID":
                    dataMsgBuilder.add("data", itemBO.generateItemID());
                    dataMsgBuilder.add("message", "Done");
                    dataMsgBuilder.add("status", "200");
                    writer.print(dataMsgBuilder.build());
                    break;

                case "GETALL":
                    resp.setStatus(HttpServletResponse.SC_OK);//201
                    dataMsgBuilder.add("data", itemBO.getAllItems());
                    dataMsgBuilder.add("message", "Done");
                    dataMsgBuilder.add("status", "200");
                    writer.print(dataMsgBuilder.build());
                    break;

                case "SEARCH":
                    String id = req.getParameter("code");
                    resp.setStatus(HttpServletResponse.SC_OK);//201
                    dataMsgBuilder.add("data", itemBO.searchItem(id));
                    dataMsgBuilder.add("message", "Done");
                    dataMsgBuilder.add("status", "200");
                    writer.print(dataMsgBuilder.build());
                    break;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

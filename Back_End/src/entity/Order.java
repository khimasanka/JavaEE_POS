package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private String oid;
    private Date date;
    private String customerId;
}

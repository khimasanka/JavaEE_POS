package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailsDTO {
    private String id;
    private String itemCode;
    private int qty;
    private double unitPrice;
}

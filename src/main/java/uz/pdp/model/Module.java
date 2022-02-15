package uz.pdp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Module {
    private UUID id;
    private String name;
    private boolean isActive;
    private double price;

}

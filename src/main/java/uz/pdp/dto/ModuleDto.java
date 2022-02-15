package uz.pdp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModuleDto {
    private UUID id;
    private String name;
    private boolean isActive;
    private double price;
    private UUID courseId;
}

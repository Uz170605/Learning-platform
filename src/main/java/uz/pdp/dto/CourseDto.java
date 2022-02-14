package uz.pdp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.User;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {
    private UUID id ;
    private String name;
    private double price;
    private String description;
    private boolean isActive;
    private List<UserDto> authors;
    private UUID[] authorsId;

}

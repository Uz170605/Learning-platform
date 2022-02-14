package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
    private UUID id ;
    private String name;
    private double price;
    private String description;
    private boolean isActive;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private List<User> authors;

}

package uz.pdp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessonDto {
    private UUID id ;
    private String name;
    private String video;
    private String task;
    private String guide;

}

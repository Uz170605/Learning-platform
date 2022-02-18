package uz.pdp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.Module;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessonDto {
    private UUID id;
    private String title;
    private ModuleDto moduleDto;
    private UUID moduleId;

}

package Api.System_Attendace.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClassEntityRequest {

    @NotNull(message = "Teacher ID is required")
    private Long teacherId;

    @NotBlank(message = "Class code is required")
    private String classCode;

    @NotBlank(message = "Class title is required")
    private String classTitle;

    @NotBlank(message = "Schedule is required")
    private String schedule;

    @NotBlank(message = "Class room is required")
    private String classRoom;

    private String description;
}
package Api.System_Attendace.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class TeacherRequest {

    @NotNull(message = "User ID is required")
    private Long userId;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Teacher code is required")
    private String teacherCode;
    private String department;

    private String phone;

}
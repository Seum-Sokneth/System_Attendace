package Api.System_Attendace.dto.Response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeacherResponse {

    private Long id;

    private Long userId;
    private String name;
    private String email;
    private String teacherCode;
    private String department;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
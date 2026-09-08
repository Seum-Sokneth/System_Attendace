package Api.System_Attendace.dto.Response;

import Api.System_Attendace.enums.Gender;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EnrollResponse {

    private Long id;

    private Long classId;

    private String classTitle;

    private Long studentId;

    private String studentName;
    private Gender gender;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
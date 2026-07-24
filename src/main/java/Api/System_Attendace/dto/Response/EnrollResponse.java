package Api.System_Attendace.dto.Response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EnrollResponse {

    private Long id;

    private Long classId;

    private String classTitle;

    private Long studentId;

    private String studentName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
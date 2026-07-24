package Api.System_Attendace.dto.Response;

import Api.System_Attendace.enums.AttendanceStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AttendanceResponse {

    private Long id;
    private Long sessionId;
    private Long studentId;
    private String studentName;
    private AttendanceStatus status;
    private String remark;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDate date;
}
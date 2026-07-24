package Api.System_Attendace.dto.Request;

import Api.System_Attendace.enums.AttendanceStatus;
import lombok.Data;

@Data
public class AttendanceRequest {
    private Long sessionId;
    private Long studentId;
    private AttendanceStatus status;
    private String remark;
}
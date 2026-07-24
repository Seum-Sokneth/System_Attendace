package Api.System_Attendace.dto.Request;

import lombok.Data;

import java.util.List;

@Data
public class AttendanceBulkRequest {

    private Long sessionId;
    private List<AttendanceRequest> records;

}
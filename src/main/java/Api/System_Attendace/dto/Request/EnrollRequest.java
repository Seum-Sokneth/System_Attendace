package Api.System_Attendace.dto.Request;

import lombok.Data;

@Data
public class EnrollRequest {
    private Long studentId;
    private Long classId;
}
package Api.System_Attendace.dto.Request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AttendanceSessionRequest {

    private Long classId;

    private LocalDate sessionDate;

    private LocalTime startAt;
}
package Api.System_Attendace.dto.Response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class AttendanceSessionResponse {

    private Long id;

    private Long classId;

    private String className;

    private LocalDate sessionDate;

    private LocalTime startAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
package Api.System_Attendace.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceReportResponse {

    private LocalDate date;

    private long present;

    private long absent;

    private long late;

    private long excuse;

    private long totalStudent;

    private double attendanceRate;
}
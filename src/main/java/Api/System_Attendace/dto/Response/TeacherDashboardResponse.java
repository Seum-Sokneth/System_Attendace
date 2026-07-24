package Api.System_Attendace.dto.Response;

import lombok.Data;

@Data
public class TeacherDashboardResponse {

    private long totalClasses;
    private long totalStudents;
    private long totalAttendance;
    private long totalPresent;
    private long totalAbsent;

    private double presentRate;
    private double absentRate;
}

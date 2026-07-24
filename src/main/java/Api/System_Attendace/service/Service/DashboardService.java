package Api.System_Attendace.service.Service;

import Api.System_Attendace.dto.Response.TeacherDashboardResponse;

public interface DashboardService {
    TeacherDashboardResponse getDashboard(Long teacherId);
}

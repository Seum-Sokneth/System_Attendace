package Api.System_Attendace.controller;

import Api.System_Attendace.dto.Response.TeacherDashboardResponse;
import Api.System_Attendace.service.Service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@AllArgsConstructor
public class TeacherDashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/{teacherId}")
    public TeacherDashboardResponse getDashboard(
            @PathVariable Long teacherId) {

        return dashboardService.getDashboard(teacherId);
    }
}
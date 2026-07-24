package Api.System_Attendace.controller;


import Api.System_Attendace.dto.Request.AttendanceSessionRequest;
import Api.System_Attendace.dto.Response.AttendanceSessionResponse;


import Api.System_Attendace.service.Service.AttendanceSessionService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/attendance-sessions")
@RequiredArgsConstructor
public class AttendanceSessionController {
    private final AttendanceSessionService attendanceSessionService;
    @PostMapping
    public ResponseEntity<AttendanceSessionResponse> create(@RequestBody AttendanceSessionRequest request){
        return new ResponseEntity<>(
                attendanceSessionService.create(request),
                HttpStatus.CREATED
        );

    }
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceSessionResponse> update(@PathVariable Long id, @RequestBody AttendanceSessionRequest request){
        return ResponseEntity.ok(
                attendanceSessionService.update(request,id)
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceSessionResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(
                attendanceSessionService.getById(id)
        );

    }
    @GetMapping
    public ResponseEntity<List<AttendanceSessionResponse>> getAll(){
        return ResponseEntity.ok(
                attendanceSessionService.getAll()
        );

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        attendanceSessionService.delete(id);
        return ResponseEntity.ok(
                "Attendance session deleted successfully"
        );

    }

}
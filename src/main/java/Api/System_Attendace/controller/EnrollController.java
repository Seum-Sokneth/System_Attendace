package Api.System_Attendace.controller;


import Api.System_Attendace.dto.Request.EnrollRequest;
import Api.System_Attendace.dto.Response.EnrollResponse;
import Api.System_Attendace.service.Service.EnrollService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/enroll")
@AllArgsConstructor
public class EnrollController {
    private final EnrollService enrollService;
    @PostMapping
    public EnrollResponse create(@RequestBody EnrollRequest request){
        return enrollService.create(request);
    }
    @GetMapping
    public List<EnrollResponse> findAll(){
        return enrollService.findAll();
    }

    @GetMapping("/{id}")
    public EnrollResponse findById(@PathVariable Long id){
        return enrollService.findById(id);
    }

    @PutMapping("/{id}")
    public EnrollResponse update(@RequestBody EnrollRequest request, @PathVariable Long id){
        return enrollService.update(request,id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        enrollService.delete(id);
        return "Enrollment deleted successfully";
    }

}
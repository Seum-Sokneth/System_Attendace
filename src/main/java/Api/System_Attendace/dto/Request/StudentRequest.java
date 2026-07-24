package Api.System_Attendace.dto.Request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentRequest {

    private String name;

    private String studentCode;

    private String email;
    private String password;
    private String phoneNumber;

    private LocalDate dateOfBirth;

}
package Api.System_Attendace.dto.Request;

import Api.System_Attendace.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentRequest {

    private String name;
    private String studentCode;
    private Gender gender;
    private String email;
    private String password;
    private String phoneNumber;

    private LocalDate dateOfBirth;

}
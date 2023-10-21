package com.nettaravel.com.com.nextrravel.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/*@AllArgsConstructor
@NoArgsConstructor
@ToString*/
@Data
public class RequestDoctorDto {
    private String name;
    private String address;
    private String contact;
    private double salary;
}

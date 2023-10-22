package com.nettaravel.com.com.nextrravel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUserDto {
    public long id;
    public String fullName;
    public String email;
    public String password;

}

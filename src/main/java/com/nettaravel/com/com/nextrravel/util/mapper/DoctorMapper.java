package com.nettaravel.com.com.nextrravel.util.mapper;



import com.nettaravel.com.com.nextrravel.dto.request.RequestDoctorDto;
import com.nettaravel.com.com.nextrravel.dto.response.ResponseDoctorDto;
import com.nettaravel.com.com.nextrravel.entity.Doctor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    ResponseDoctorDto toResponseDoctorDto(Doctor doctor);
    Doctor toDoctor(RequestDoctorDto dto);
    List<ResponseDoctorDto> toResponseDoctorDtoList(List<Doctor> list);
}

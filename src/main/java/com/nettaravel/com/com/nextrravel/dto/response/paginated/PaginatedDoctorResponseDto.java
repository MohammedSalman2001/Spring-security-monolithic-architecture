package com.nettaravel.com.com.nextrravel.dto.response.paginated;


import com.nettaravel.com.com.nextrravel.dto.response.ResponseDoctorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedDoctorResponseDto {
    private long count;
    private List<ResponseDoctorDto> dataList;
}

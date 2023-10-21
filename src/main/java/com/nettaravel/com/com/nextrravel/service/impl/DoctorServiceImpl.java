package com.nettaravel.com.com.nextrravel.service.impl;


import com.nettaravel.com.com.nextrravel.dto.request.RequestDoctorDto;
import com.nettaravel.com.com.nextrravel.dto.response.ResponseDoctorDto;
import com.nettaravel.com.com.nextrravel.dto.response.paginated.PaginatedDoctorResponseDto;
import com.nettaravel.com.com.nextrravel.entity.Doctor;
import com.nettaravel.com.com.nextrravel.exceptions.EntryNotFoundException;
import com.nettaravel.com.com.nextrravel.repo.DoctorRepo;
import com.nettaravel.com.com.nextrravel.service.DoctorService;
import com.nettaravel.com.com.nextrravel.util.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepo doctorRepo;

    private final DoctorMapper doctorMapper;
    @Autowired
    public DoctorServiceImpl(DoctorRepo doctorRepo, DoctorMapper doctorMapper) {
        this.doctorRepo = doctorRepo;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public void createDoctor(RequestDoctorDto dto) {

        UUID uuid = UUID.randomUUID();
        long docId = uuid.getMostSignificantBits();

        Doctor doctor = new Doctor(
                docId, dto.getName(), dto.getAddress(),
                dto.getContact(), dto.getSalary()
        );

        doctorRepo.save(doctor);
    }

    @Override
    public ResponseDoctorDto getDoctor(long id) {
        Optional<Doctor> selectedDoctor = doctorRepo.findById(id);
        if (selectedDoctor.isEmpty()) {
            throw new EntryNotFoundException("Doctor Not Found");
        }
        return doctorMapper.toResponseDoctorDto(selectedDoctor.get());
    }

    @Override
    public void deleteDoctor(long id) {
        Optional<Doctor> selectedDoctor = doctorRepo.findById(id);
        if (selectedDoctor.isEmpty()) {
            throw new EntryNotFoundException("Doctor Not Found");
        }
        doctorRepo.deleteById(selectedDoctor.get().getId());
    }

    @Override
    public List<ResponseDoctorDto> findDoctorsByName(String name) {
        List<Doctor> allByName = doctorRepo.findAllByName(name);
        return null;
    }

    @Override
    public void updateDoctor(long id, RequestDoctorDto dto) {
        Optional<Doctor> selectedDoctor = doctorRepo.findById(id);
        if (selectedDoctor.isEmpty()) {
            throw new EntryNotFoundException("Doctor Not Found");
        }
        Doctor doc = selectedDoctor.get();
        doc.setName(dto.getName());
        doc.setAddress(dto.getAddress());
        doc.setSalary(dto.getSalary());
        doc.setContact(dto.getContact());
        doctorRepo.save(doc);
    }

    @Override
    public PaginatedDoctorResponseDto getAllDoctors(String searchText, int page, int size) {
        searchText = "%" + searchText + "%";
        List<Doctor> doctors = doctorRepo.searchDoctors(searchText, PageRequest.of(page, size));
        long doctorCount = doctorRepo.countDoctors(searchText);
        List<ResponseDoctorDto> dtos = doctorMapper.toResponseDoctorDtoList(doctors);
        return new PaginatedDoctorResponseDto(
                doctorCount,
                dtos
        );
    }
}

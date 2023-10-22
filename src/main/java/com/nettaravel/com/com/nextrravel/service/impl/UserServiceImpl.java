package com.nettaravel.com.com.nextrravel.service.impl;

import com.nettaravel.com.com.nextrravel.dto.request.RequestUserDto;
import com.nettaravel.com.com.nextrravel.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Override
    public void signup(RequestUserDto dto) {

    }
}

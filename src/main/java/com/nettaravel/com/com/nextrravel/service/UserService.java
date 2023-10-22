package com.nettaravel.com.com.nextrravel.service;

import com.nettaravel.com.com.nextrravel.dto.request.RequestUserDto;

public interface UserService {
    public void signup(RequestUserDto dto);

    public boolean verifyUser(String type,String token);
}

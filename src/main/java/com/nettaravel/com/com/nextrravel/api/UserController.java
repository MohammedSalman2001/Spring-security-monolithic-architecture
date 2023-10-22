package com.nettaravel.com.com.nextrravel.api;

import com.nettaravel.com.com.nextrravel.dto.request.RequestUserDto;
import com.nettaravel.com.com.nextrravel.service.UserService;
import com.nettaravel.com.com.nextrravel.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/visitor/signup")
    public ResponseEntity<StandardResponse> createDoctor(@RequestBody RequestUserDto dto){
     userService.signup(dto);
        return new ResponseEntity<>(
                new StandardResponse(201,"doctor was saved!",dto.getEmail()),
                HttpStatus.CREATED
        );
    }
    @GetMapping(path = "/verify",params = {"type"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DOCTOR')")
    public ResponseEntity<StandardResponse>verifyUser(@RequestParam String type,@RequestHeader("Authorization") String token){
//        boolean verifyUser = userService.verifyUser(type, token);
        return new ResponseEntity<>(
                new StandardResponse(201,"user state!",userService.verifyUser(type,token)),
                HttpStatus.OK
        );
    }
}

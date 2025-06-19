package dev.brenolucks.clicker_backend.controller;

import dev.brenolucks.clicker_backend.domain.dto.UserLoginResponseDTO;
import dev.brenolucks.clicker_backend.domain.dto.UserRegisterResponseDTO;
import dev.brenolucks.clicker_backend.domain.dto.UserRequestDTO;
import dev.brenolucks.clicker_backend.service.UsersServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class UsersController {
    private final UsersServiceImpl userServiceImpl;

    public UsersController(UsersServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> userLogin(@RequestBody UserRequestDTO userRequestDTO) {
        userServiceImpl.generateRandomNumberAndClicks(userRequestDTO.username());
        return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.loginUser(userRequestDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> userRegister(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImpl.registerUser(userRequestDTO));
    }
}

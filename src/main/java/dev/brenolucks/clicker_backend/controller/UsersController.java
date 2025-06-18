package dev.brenolucks.clicker_backend.controller;

import dev.brenolucks.clicker_backend.domain.dto.UserRequestDTO;
import dev.brenolucks.clicker_backend.domain.dto.UserResponseDTO;
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
    public ResponseEntity<UserResponseDTO> userLogin(@RequestBody UserRequestDTO userRequestDTO) {
        userServiceImpl.generateRandomNumber(userRequestDTO.username());
        return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.loginUser(userRequestDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@RequestBody UserRequestDTO userRequestDTO) {
        userServiceImpl.registerUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered with success!");
    }

    @GetMapping("/clicker")
    public ResponseEntity<String> clicou() {
        System.out.println("ENTROU NO MÉTODO CLICOU");
        return ResponseEntity.status(HttpStatus.OK).body("CLICLADO");
    }
}

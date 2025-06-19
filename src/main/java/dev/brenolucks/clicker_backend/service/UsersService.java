package dev.brenolucks.clicker_backend.service;

import dev.brenolucks.clicker_backend.domain.dto.UserLoginResponseDTO;
import dev.brenolucks.clicker_backend.domain.dto.UserRegisterResponseDTO;
import dev.brenolucks.clicker_backend.domain.dto.UserRequestDTO;

public interface UsersService {
    UserLoginResponseDTO loginUser(UserRequestDTO userRequestDTO);
    UserRegisterResponseDTO registerUser(UserRequestDTO userRequestDTO);
    void generateRandomNumberAndClicks(String username);

}

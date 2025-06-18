package dev.brenolucks.clicker_backend.service;

import dev.brenolucks.clicker_backend.domain.dto.UserRequestDTO;
import dev.brenolucks.clicker_backend.domain.dto.UserResponseDTO;

public interface UsersService {
    UserResponseDTO loginUser(UserRequestDTO userRequestDTO);
    void registerUser(UserRequestDTO userRequestDTO);
}

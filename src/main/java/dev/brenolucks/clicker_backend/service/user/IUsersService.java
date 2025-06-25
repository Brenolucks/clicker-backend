package dev.brenolucks.clicker_backend.service.user;

import dev.brenolucks.clicker_backend.domain.dto.user.UserLoginResponseDTO;
import dev.brenolucks.clicker_backend.domain.dto.user.UserRegisterResponseDTO;
import dev.brenolucks.clicker_backend.domain.dto.user.UserRequestDTO;

public interface IUsersService {
    UserLoginResponseDTO loginUser(UserRequestDTO userRequestDTO);
    UserRegisterResponseDTO registerUser(UserRequestDTO userRequestDTO);
    void generateRandomNumberAndClicks(String username);

}

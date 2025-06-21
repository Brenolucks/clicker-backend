package dev.brenolucks.clicker_backend.service.user;

import dev.brenolucks.clicker_backend.domain.dto.user.UserLoginResponseDTO;
import dev.brenolucks.clicker_backend.domain.dto.user.UserRegisterResponseDTO;
import dev.brenolucks.clicker_backend.domain.dto.user.UserRequestDTO;
import dev.brenolucks.clicker_backend.domain.model.Users;
import dev.brenolucks.clicker_backend.exceptions.UserExistException;
import dev.brenolucks.clicker_backend.exceptions.UserNotExistException;
import dev.brenolucks.clicker_backend.repositories.UsersRepository;
import dev.brenolucks.clicker_backend.utils.ClickerUtils;
import dev.brenolucks.clicker_backend.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final ClickerUtils clickerUtils;

    public UsersServiceImpl(UsersRepository usersRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtUtils jwtUtils, ClickerUtils clickerUtils) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.clickerUtils = clickerUtils;
    }

    @Override
    public UserLoginResponseDTO loginUser(UserRequestDTO userRequestDTO) {
        var existUser = usersRepository.findByUsername(userRequestDTO.username());
        if(existUser.isEmpty()) throw new UserNotExistException(String.format("User with this username: %s not exist", userRequestDTO.username()));

        var user = new UsernamePasswordAuthenticationToken(userRequestDTO.username(), userRequestDTO.password());

        var userAuthenticated = authenticationManager.authenticate(user);
        var token = jwtUtils.generateToken((Users) userAuthenticated.getPrincipal());

        return new UserLoginResponseDTO(user.getName(), existUser.get().getEmail(), existUser.get().getRandomNumber(),
                existUser.get().getAvaliableClick(), token);
    }

    @Override
    public UserRegisterResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        var existUserEmail = usersRepository.findFirstByEmail(userRequestDTO.email());
        var existUserUsername = usersRepository.findFirstByUsername(userRequestDTO.username());

        if(existUserEmail.isPresent()) throw new UserExistException(String.format("User with this email: %s, already exists!", userRequestDTO.email()));
        if(existUserUsername.isPresent()) throw new UserExistException(String.format("User with this username: %s, already exists!", userRequestDTO.username()));

        var passwordEncrypted = passwordEncoder.encode(userRequestDTO.password());

        var newUser = new Users(userRequestDTO.username(), userRequestDTO.email(), passwordEncrypted);
        usersRepository.save(newUser);

        return new UserRegisterResponseDTO(newUser.getUsername(), newUser.getEmail());
    }

    @Override
    public void generateRandomNumberAndClicks(String username) {
        var userFound = usersRepository.findByUsername(username);
        var user = userFound.orElseThrow(() -> new UserNotExistException(String.format("User with this username: %s, doesn't exist!", username)));
        var randomNumber = clickerUtils.generateRandomNUmber();
        var availableClicks = clickerUtils.generateAvailableClicks(randomNumber);

        if(user.getRandomNumber() == 0) {
            user.setRandomNumber(randomNumber);
            user.setAvaliableClick(availableClicks);
            usersRepository.save(user);
        }
    }
}

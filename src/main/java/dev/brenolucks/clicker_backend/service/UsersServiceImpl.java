package dev.brenolucks.clicker_backend.service;

import dev.brenolucks.clicker_backend.domain.dto.UserRequestDTO;
import dev.brenolucks.clicker_backend.domain.dto.UserResponseDTO;
import dev.brenolucks.clicker_backend.domain.model.Users;
import dev.brenolucks.clicker_backend.repositories.UsersRepository;
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

    public UsersServiceImpl(UsersRepository usersRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtUtils jwtUtils) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserResponseDTO loginUser(UserRequestDTO userRequestDTO) {
        //check in DB
        var existUser = usersRepository.findByUsername(userRequestDTO.username());
        if(existUser.isEmpty()) throw new RuntimeException("User not exist");

        var user = new UsernamePasswordAuthenticationToken(userRequestDTO.username(), userRequestDTO.password());
        System.out.println(user);
        var userAuthenticated = authenticationManager.authenticate(user);
        var token = jwtUtils.generateToken((Users) userAuthenticated.getPrincipal());

        System.out.println("TOKEN: " + token);

        return new UserResponseDTO(user.getName(), userRequestDTO.email(), token);
    }

    @Override
    public void registerUser(UserRequestDTO userRequestDTO) {
        var existUser = usersRepository.findByUsername(userRequestDTO.email());
        if(existUser.isPresent()) throw new RuntimeException("User already exists!");

        var passwordEncrypted = passwordEncoder.encode(userRequestDTO.password());

        var newUser = new Users(userRequestDTO.username(), userRequestDTO.email(), passwordEncrypted);
        usersRepository.save(newUser);
    }

    @Override
    public void generateRandomNumber(String username) {
        var result = usersRepository.findRandomNumberByUsername(username);
        var user = result.orElseThrow(() -> new RuntimeException("User doesn't exist!"));

        if(user.getRandomNumber() == 0) {
            int randomNumber = (int) (Math.random() * 101);
            user.setRandomNumber(randomNumber);
            usersRepository.save(user);
        } else {
            throw new RuntimeException("Number already exists for this user.");
        }
    }
}

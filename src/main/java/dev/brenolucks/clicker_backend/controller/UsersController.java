package dev.brenolucks.clicker_backend.controller;

import dev.brenolucks.clicker_backend.domain.dto.exceptions.ErrorResponseDTO;
import dev.brenolucks.clicker_backend.domain.dto.user.LeadboardResponse;
import dev.brenolucks.clicker_backend.domain.dto.user.UserLoginResponseDTO;
import dev.brenolucks.clicker_backend.domain.dto.user.UserRegisterResponseDTO;
import dev.brenolucks.clicker_backend.domain.dto.user.UserRequestDTO;
import dev.brenolucks.clicker_backend.service.user.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Tag(name = "User", description = "Endpoints for user")
public class UsersController {
    private final UsersService userServiceImpl;

    public UsersController(UsersService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Operation(summary = "Login endpoint", description = "Here is the login endpoint, it should be return if you were able to log or not!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserLoginResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "User suplied not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)))})
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> userLogin(@RequestBody UserRequestDTO userRequestDTO) {
        userServiceImpl.generateRandomNumberAndClicks(userRequestDTO.username());
        return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.loginUser(userRequestDTO));
    }

    @Operation(summary = "Register endpoint", description = "Here is the registration endpoint, it should be returned if you were able to register or not!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRegisterResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "User suplied not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)))})
    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> userRegister(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImpl.registerUser(userRequestDTO));
    }

    @Operation(summary = "Leadboard endpoint", security = @SecurityRequirement( name = "bearerAuth"),
            description = "Here is the leadboard endpoint, it should be a list with all winner and your attempts to won the game.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LeadboardResponse.class)))})
    @GetMapping("/leadboard")
    public ResponseEntity<List<LeadboardResponse>> getLeadboard() {
        return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.leadboard());
    }
}

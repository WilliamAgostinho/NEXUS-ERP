package com.nexus.nexus_api.service;

import com.nexus.nexus_api.dto.LoginRequestDto;
import com.nexus.nexus_api.dto.LoginResponseDto;
import com.nexus.nexus_api.exception.BusinessException;
import com.nexus.nexus_api.exception.ResourceNotFoundException;
import com.nexus.nexus_api.repository.EmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService (EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        var user = employeeRepository
                .findByemployeeUserName(loginRequestDto.getLogin())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        boolean validPassword = passwordEncoder.matches(
                loginRequestDto.getPassword(),
                user.getEmployeePasswordHash()
        );

        if (!validPassword) {
            throw new BusinessException("Invalid password");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponseDto(token);
    }
}

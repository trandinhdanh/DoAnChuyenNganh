package com.techpower.exammanagement.service.impl;

import com.techpower.exammanagement.constant.UserConstant;
import com.techpower.exammanagement.dto.UserDTO;
import com.techpower.exammanagement.repository.RoleRepository;
import com.techpower.exammanagement.repository.UserRepository;
import com.techpower.exammanagement.auth.AuthenticationRequest;
import com.techpower.exammanagement.auth.AuthenticationResponse;
import com.techpower.exammanagement.constant.RoleConstant;
import com.techpower.exammanagement.entity.RoleEntity;
import com.techpower.exammanagement.entity.UserEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

//    public AuthenticationResponse register(UserDTO request) {
//        var user = UserEntity.builder()
//                .username(request.getUsername())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .status(UserConstant.ACTIVE)
//                .roles(roles())
//                .build();
//        userRepository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }

//    private List<RoleEntity> roles() {
//        List<RoleEntity> result = new ArrayList<>();
//        result.add(roleRepository.findOneByName(RoleConstant.STUDENT));
//        return result;
//    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

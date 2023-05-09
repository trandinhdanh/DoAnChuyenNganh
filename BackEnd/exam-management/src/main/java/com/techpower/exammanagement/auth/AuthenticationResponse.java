package com.techpower.exammanagement.auth;

import com.techpower.exammanagement.dto.UserDTO;

public record AuthenticationResponse (
        String token,
        UserDTO userDTO){
}

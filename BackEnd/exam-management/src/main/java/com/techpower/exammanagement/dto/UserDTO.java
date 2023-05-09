package com.techpower.exammanagement.dto;

import com.techpower.exammanagement.constant.Status;

import java.util.List;

public record UserDTO(
        Long id,
        String userName,
        Status status,
        List<String> role

//         Integer id,
//        String name,
//        String email,
//        Gender gender,
//        Integer age,
//        String username
){

}

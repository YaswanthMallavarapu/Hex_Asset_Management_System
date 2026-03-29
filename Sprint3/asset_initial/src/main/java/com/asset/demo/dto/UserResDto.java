package com.asset.demo.dto;

import com.asset.demo.enums.UserStatus;

public record UserResDto(
        long id,
        String firstName,
        String lastName,
        String email,
        String designation,
        UserStatus status
) {
}

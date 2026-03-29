package com.asset.demo.mapper;

import com.asset.demo.dto.UserReqDto;
import com.asset.demo.dto.UserResDto;
import com.asset.demo.model.User;

public class UserMapper {

    public static User mapToEntity(UserReqDto userReqDto){
        User user=new User();
        user.setFirstName(userReqDto.firstName());
        user.setLastName(userReqDto.lastName());
        user.setGender(userReqDto.gender());
        user.setContactNumber(userReqDto.contactNumber());
        user.setDesignation(userReqDto.designation());
        user.setEmail(userReqDto.email());

        user.setPassword(userReqDto.password());
        user.setRole(userReqDto.role());
        return user;
    }
    public static UserResDto mapToDto(User user){
        return new UserResDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getDesignation(),
                user.getStatus()
        );
    }
}

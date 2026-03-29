package com.asset.demo.controller;

import com.asset.demo.dto.UserReqDto;
import com.asset.demo.dto.UserResDto;
import com.asset.demo.model.User;
import com.asset.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    /* Access : All */
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid  @RequestBody UserReqDto userReqDto){

        userService.addUser(userReqDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
    /* Access : ADMIN */
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUsers(@RequestParam(value = "page",required = false,defaultValue = "0") int page,
                                                        @RequestParam(value = "size",required = false,defaultValue = "5")int size
                            ){
        List<UserResDto> list=userService.getAllUsers(page,size);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

    /* Access : ADMIN , EMPLOYEE */
    @GetMapping("/get/{id}")
    public UserResDto getUserById(@PathVariable long id){
        return userService.getUserById(id);

    }
}

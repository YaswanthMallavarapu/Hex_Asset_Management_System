package com.asset.demo.controller;

import com.asset.demo.dto.AssetCategoryReqDto;
import com.asset.demo.model.AssetCategory;
import com.asset.demo.service.AssetCategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/asset-category")
public class AssetCategoryController {
    private final AssetCategoryService assetCategoryService;

    /* Access : ADMIN */
    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@Valid @RequestBody AssetCategoryReqDto assetCategoryReqDto){

        assetCategoryService.addCategory(assetCategoryReqDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();

    }

    /* Access : ADMIN , EMPLOYEE */
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllAssetCategory(){
        List<AssetCategory> list=assetCategoryService.getAllAssetCategory();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }
}

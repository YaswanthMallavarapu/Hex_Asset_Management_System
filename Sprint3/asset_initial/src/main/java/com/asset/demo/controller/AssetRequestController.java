package com.asset.demo.controller;

import com.asset.demo.dto.AssetRequestReqdto;
import com.asset.demo.dto.AssetRequestResDto;
import com.asset.demo.model.AssetRequest;
import com.asset.demo.service.AssetRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/asset-request")
public class AssetRequestController {

    private final AssetRequestService assetRequestService;
    /* Access : ADMIN , EMPLOYEE */
    @PostMapping("/add/{employeeId}/{assetId}")
    public ResponseEntity<?> requestAsset(@RequestBody AssetRequestReqdto assetRequestReqdto, @PathVariable long employeeId, @PathVariable long assetId){
        assetRequestService.requestAsset(assetRequestReqdto,employeeId,assetId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();

    }
    /* Access : ADMIN  */
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllAssetRequests(@RequestParam(value = "page",required = false,defaultValue = "0")int page,
                                                                  @RequestParam(value = "size",required = false,defaultValue = "5")int size){
        List<AssetRequestResDto> list=assetRequestService.getAllAssetRequests(page,size);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

}

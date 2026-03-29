package com.asset.demo.controller;

import com.asset.demo.model.AssetAllocation;
import com.asset.demo.service.AssetAllocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asset-allocation")
@AllArgsConstructor
public class AssetAllocationController {
    private final AssetAllocationService assetAllocationService;

    /* Access : ADMIN */
    @PostMapping("/allocate/{employeeId}/{assetId}/{assetRequestId}")
    public ResponseEntity<?> allocateAsset(@PathVariable long employeeId,
                                                @PathVariable long assetId,
                                           @PathVariable long assetRequestId){
        assetAllocationService.allocateAsset(employeeId,assetId,assetRequestId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();

    }

    /* Access : ADMIN */
    @PostMapping("/reject/{employeeId}/{assetId}/{assetRequestId}")
    public ResponseEntity<?> rejectRequest(@PathVariable long employeeId,
                                           @PathVariable long assetId,
                                           @PathVariable long assetRequestId){
        assetAllocationService.rejectAsset(employeeId,assetId,assetRequestId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();

    }

    /* Access : ADMIN, EMPLOYEE */
    @PutMapping("/return-asset/{employeeId}/{assetId}/{assetAllocationId}")
    public ResponseEntity<?> returnAsset(
            @PathVariable long employeeId,
            @PathVariable long assetId,
            @PathVariable long assetAllocationId
    ){

        assetAllocationService.returnAsset(employeeId,assetId,assetAllocationId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }

}

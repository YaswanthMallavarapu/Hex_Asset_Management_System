package com.asset.demo.controller;

import com.asset.demo.dto.AssetAuditReqDto;
import com.asset.demo.service.AssetAuditService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/asset-audit")
@RestController
@AllArgsConstructor
public class AssetAuditController {
    private final AssetAuditService assetAuditService;
    /* Access : ADMIN */
    @PostMapping("/audit/{employeeId}/{assetId}")
    public void auditAsset(
            @RequestBody AssetAuditReqDto assetAuditReqDto,
            @PathVariable long employeeId,
            @PathVariable long assetId
    ){
        assetAuditService.auditAsset(assetAuditReqDto,employeeId,assetId);

    }
}

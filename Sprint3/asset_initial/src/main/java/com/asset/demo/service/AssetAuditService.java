package com.asset.demo.service;

import com.asset.demo.dto.AssetAuditReqDto;
import com.asset.demo.enums.AssetStatus;
import com.asset.demo.enums.AuditStatus;
import com.asset.demo.exceptions.ResourceNotFoundException;
import com.asset.demo.model.Asset;
import com.asset.demo.model.AssetAudit;
import com.asset.demo.model.User;
import com.asset.demo.repository.AssetAuditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AssetAuditService {
    private final AssetAuditRepository assetAuditRepository;

    private final UserService userService;
    private final AssetService assetService;
    public void auditAsset(AssetAuditReqDto assetAuditReqDto, long employeeId, long assetId) {
        //check for employee
        User user=userService.getUserByGivenId(employeeId);
        //check for asset
        Asset asset=assetService.getAssetByGivenId(assetId);
        //check for validity of asset allocated or not
        if(asset.getStatus()== AssetStatus.AVAILABLE ||
        asset.getStatus()==AssetStatus.RETIRED)
            throw new ResourceNotFoundException("Invalid asset for audit");
        //add additional details to audit
        AssetAudit assetAudit=new AssetAudit();
        assetAudit.setEmployee(user);
        assetAudit.setAsset(asset);
        assetAudit.setStatus(AuditStatus.VERIFIED);
        assetAudit.setRemarks(assetAuditReqDto.remarks());
        //save asset audit status in DB
        assetAuditRepository.save(assetAudit);
    }
}

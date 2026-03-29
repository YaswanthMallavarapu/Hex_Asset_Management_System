package com.asset.demo.service;

import com.asset.demo.enums.AllocationStatus;
import com.asset.demo.enums.AssetStatus;
import com.asset.demo.enums.RequestStatus;
import com.asset.demo.exceptions.ResourceNotFoundException;
import com.asset.demo.model.Asset;
import com.asset.demo.model.AssetAllocation;
import com.asset.demo.model.AssetRequest;
import com.asset.demo.model.User;
import com.asset.demo.repository.AssetAllocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@AllArgsConstructor
public class AssetAllocationService {
    private final AssetAllocationRepository assetAllocationRepository;
    private final UserService userService;
    private final AssetService assetService;
    private final AssetRequestService assetRequestService;
    public void allocateAsset(long employeeId, long assetId, long assetRequestId) {
        //check for employee
        User user=userService.getUserByGivenId(employeeId);
        //check for asset
        Asset asset=assetService.getAssetByGivenId(assetId);
        //get asset request
        AssetRequest assetRequest=assetRequestService.getAssetRequestById(assetRequestId);
        //check weather valid or not
        if(assetRequest.getEmployee().getId()!=employeeId ||
                assetRequest.getAsset().getId()!=assetId)
            throw new ResourceNotFoundException("Invalid asset or employee for this asset allocation");
        // check weather the asset is available
        if(asset.getStatus()!=AssetStatus.AVAILABLE)
            throw new ResourceNotFoundException("Asset not available at the moment.");

        // make allocation true
        assetRequest.setStatus(RequestStatus.APPROVED);
        assetRequestService.updateAssetRequestStatus(assetRequest);
        asset.setStatus(AssetStatus.ALLOCATED);
        assetService.updateAsset(asset);
        //add employee and asset to assetAllocation
        AssetAllocation assetAllocation=new AssetAllocation();
        assetAllocation.setEmployee(user);
        assetAllocation.setAsset(asset);
        //save asset allocation in DB
        assetAllocationRepository.save(assetAllocation);
    }

    public void rejectAsset(long employeeId, long assetId, long assetRequestId) {
        //check for employee
        User user=userService.getUserByGivenId(employeeId);
        //check for asset
        Asset asset=assetService.getAssetByGivenId(assetId);
        //get asset request
        AssetRequest assetRequest=assetRequestService.getAssetRequestById(assetRequestId);
        //check weather valid or not
        if(assetRequest.getEmployee().getId()!=employeeId ||
                assetRequest.getAsset().getId()!=assetId)
            throw new ResourceNotFoundException("Invalid asset or employee for this asset allocation");
        //make allocation rejected
//        if(assetRequest.getStatus()!=RequestStatus.PENDING)
//            throw new ResourceNotFoundException("You can reject a asset that is already approved or rejected");
        assetRequest.setStatus(RequestStatus.REJECTED);
        assetRequestService.updateAssetRequestStatus(assetRequest);
//        as asset request is rejected there is no need to add it in asset allocation
//        //add employee and asset to assetAllocation
//        AssetAllocation assetAllocation=new AssetAllocation();
//        assetAllocation.setEmployee(user);
//        assetAllocation.setAsset(asset);
//        //save asset allocation in DB
//        assetAllocationRepository.save(assetAllocation);
    }

    public AssetAllocation getAssetAllocationById(long assetAllocationId) {
        return assetAllocationRepository.findById(assetAllocationId)
                .orElseThrow(()->new ResourceNotFoundException("Invalid Asset Allocation Id."));
    }

    public void returnAsset(long employeeId, long assetId, long assetAllocationId) {
        //check for employee
        User user=userService.getUserByGivenId(employeeId);
        //check for asset
        Asset asset=assetService.getAssetByGivenId(assetId);
        //get assetAllocation
        AssetAllocation assetAllocation=getAssetAllocationById(assetAllocationId);
        //check request is valid or not
        if(assetAllocation.getEmployee().getId()!=employeeId ||
        assetAllocation.getAsset().getId()!=assetId)
            throw new ResourceNotFoundException("Invalid return request");
        //update allocation status to returned and set returned date
        if(assetAllocation.getStatus()!=AllocationStatus.ALLOCATED)
            throw new ResourceNotFoundException("You can not return an asset that you dont have.");
        assetAllocation.setStatus(AllocationStatus.RETURNED);
        assetAllocation.setReturnDate(LocalDate.now());

        //update asset status to available
        asset.setStatus(AssetStatus.AVAILABLE);
        //save updated statuses in DB
        assetService.updateAsset(asset);
        assetAllocationRepository.save(assetAllocation);

    }
}

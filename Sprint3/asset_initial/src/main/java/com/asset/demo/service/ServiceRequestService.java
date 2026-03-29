package com.asset.demo.service;

import com.asset.demo.dto.ServiceRequestReqDto;
import com.asset.demo.enums.AssetStatus;
import com.asset.demo.enums.ServiceStatus;
import com.asset.demo.exceptions.ResourceNotFoundException;
import com.asset.demo.model.Asset;
import com.asset.demo.model.AssetAllocation;
import com.asset.demo.model.ServiceRequest;
import com.asset.demo.model.User;
import com.asset.demo.repository.ServiceRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceRequestService {
    private final ServiceRequestRepository serviceRequestRepository;

    private final UserService userService;
    private final AssetService assetService;
    private final AssetAllocationService assetAllocationService;
    public void requestService(ServiceRequestReqDto serviceRequestReqDto, long employeeId, long assetId, long assetAllocationId) {
        //check for employee
        User user=userService.getUserByGivenId(employeeId);
        //check for asset
        Asset asset=assetService.getAssetByGivenId(assetId);
        //check for asset allocation
        AssetAllocation assetAllocation=assetAllocationService.getAssetAllocationById(assetAllocationId);
        //check if employee has this allocated asset with him
        if(assetAllocation.getEmployee().getId()!=employeeId){
            throw new ResourceNotFoundException("You can not request service for others assets.");
        }
        if(assetAllocation.getAsset().getId()!=assetId){
            throw new ResourceNotFoundException("You can not request service for Asset that you dont have.");
        }
        //add details to service request
        ServiceRequest serviceRequest=new ServiceRequest();
        serviceRequest.setEmployee(user);
        serviceRequest.setAsset(asset);
        serviceRequest.setDescription(serviceRequestReqDto.description());
        //save assetRequest in DB
        serviceRequestRepository.save(serviceRequest);
    }

    public void acceptRequest(long serviceRequestId) {
        //get service request
        ServiceRequest serviceRequest=serviceRequestRepository.findById(serviceRequestId)
                .orElseThrow(()->new ResourceNotFoundException("Invalid Service Request Id."));
        serviceRequest.setStatus(ServiceStatus.IN_PROGRESS);
        Asset asset=serviceRequest.getAsset();
        //update asset status
        asset.setStatus(AssetStatus.IN_SERVICE);
        asset=assetService.updateAsset(asset);
        serviceRequest.setAsset(asset);
        //save updated request
        serviceRequestRepository.save(serviceRequest);
    }

    public void rejectRequest(long serviceRequestId) {
        //get service request
        ServiceRequest serviceRequest=serviceRequestRepository.findById(serviceRequestId)
                .orElseThrow(()->new ResourceNotFoundException("Invalid Service Request Id."));
        serviceRequest.setStatus(ServiceStatus.REJECTED);
        //save updated request
        serviceRequestRepository.save(serviceRequest);
    }

    public void resolvedRequest(long serviceRequestId) {
        //get service request
        ServiceRequest serviceRequest=serviceRequestRepository.findById(serviceRequestId)
                .orElseThrow(()->new ResourceNotFoundException("Invalid Service Request Id."));
        serviceRequest.setStatus(ServiceStatus.RESOLVED);
        Asset asset=serviceRequest.getAsset();
        //update asset status
        asset.setStatus(AssetStatus.ALLOCATED);
        asset=assetService.updateAsset(asset);
        serviceRequest.setAsset(asset);
        //save updated request
        serviceRequestRepository.save(serviceRequest);
    }
}

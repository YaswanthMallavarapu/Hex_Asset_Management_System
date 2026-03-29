package com.asset.demo.controller;

import com.asset.demo.dto.ServiceRequestReqDto;
import com.asset.demo.service.ServiceRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/service-request")
@RestController
@AllArgsConstructor
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;

    /* Access : ADMIN,EMPLOYEE */
    @PostMapping("/request-service/{employeeId}/{assetId}/{assetAllocationId}")
    public ResponseEntity<?> requestService(@RequestBody ServiceRequestReqDto serviceRequestReqDto,
                                            @PathVariable long employeeId,
                                            @PathVariable long assetId,
                                            @PathVariable long assetAllocationId){
        serviceRequestService.requestService(serviceRequestReqDto,employeeId,assetId,assetAllocationId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();

    }

    /* Access : ADMIN */
    @PutMapping("/accept-service-request/{serviceRequestId}")
    public ResponseEntity<?> acceptServiceRequest(@PathVariable long serviceRequestId){


        serviceRequestService.acceptRequest(serviceRequestId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }
    /* Access : ADMIN */
    @PutMapping("/reject-service-request/{serviceRequestId}")
    public ResponseEntity<?> rejectServiceRequest(@PathVariable long serviceRequestId){


        serviceRequestService.rejectRequest(serviceRequestId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }
    /* Access : ADMIN */
    @PutMapping("/resolved-service-request/{serviceRequestId}")
    public ResponseEntity<?> resolvedServiceRequest(@PathVariable long serviceRequestId){


        serviceRequestService.resolvedRequest(serviceRequestId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }
}

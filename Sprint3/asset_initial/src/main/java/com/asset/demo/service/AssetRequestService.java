package com.asset.demo.service;

import com.asset.demo.dto.AssetRequestReqdto;
import com.asset.demo.dto.AssetRequestResDto;
import com.asset.demo.exceptions.ResourceNotFoundException;
import com.asset.demo.mapper.AssetRequestMapper;
import com.asset.demo.model.Asset;
import com.asset.demo.model.AssetRequest;
import com.asset.demo.model.User;
import com.asset.demo.repository.AssetRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AssetRequestService {
    private final AssetRequestRepository assetRequestRepository;
    private final AssetService assetService;
    private final UserService userService;
    private final ResourcePatternResolver resourcePatternResolver;

    public void requestAsset(AssetRequestReqdto assetRequestReqdto, long employeeId, long assetId) {
        //check for employeeId
        User user=userService.getUserByGivenId(employeeId);
        //check for assetId
        Asset asset=assetService.getAssetByGivenId(assetId);
        //map assetRequestDto to entity
        AssetRequest assetRequest= AssetRequestMapper.mapToEntity(assetRequestReqdto);
        //add employee and asset to assetRequest
        assetRequest.setEmployee(user);
        assetRequest.setAsset(asset);
        //save assetRequest
        assetRequestRepository.save(assetRequest);
    }

    public List<AssetRequestResDto> getAllAssetRequests(int page, int size) {
        Pageable pageable=PageRequest.of(page,size);
        Page<AssetRequest> list=assetRequestRepository.findAll(pageable);
        return list
                .toList()
                .stream()
                .map(AssetRequestMapper::mapToDto)
                .toList();
    }

    public AssetRequest getAssetRequestById(long assetRequestId) {
        return assetRequestRepository.findById(assetRequestId)
                .orElseThrow(()->new ResourceNotFoundException("Invalid asset request id."));
    }

    public void updateAssetRequestStatus(AssetRequest assetRequest) {
        assetRequestRepository.save(assetRequest);
    }
}

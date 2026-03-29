package com.asset.demo.service;

import com.asset.demo.dto.AssetReqDto;
import com.asset.demo.dto.AssetResdto;
import com.asset.demo.exceptions.ResourceNotFoundException;
import com.asset.demo.mapper.AssetMapper;
import com.asset.demo.model.Asset;
import com.asset.demo.model.AssetCategory;
import com.asset.demo.repository.AssetRepository;
import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AssetService {
    private final AssetRepository assetRepository;
    private final AssetCategoryService assetCategoryService;

    public void addAsset(@Valid AssetReqDto assetReqDto, long categoryId) {
        //check for category
        AssetCategory assetCategory=assetCategoryService.getById(categoryId);

        //map dto to entity
        Asset asset= AssetMapper.mapToEntity(assetReqDto);
        //add category to asset
        asset.setCategory(assetCategory);
        //add to DB
        assetRepository.save(asset);

    }

    public List<AssetResdto> getAllAssets(int page,int size) {
        Pageable pageable=PageRequest.of(page,size);
        Page<Asset> list=assetRepository.findAll(pageable);
        return list
                .toList()
                .stream()
                .map(AssetMapper::mapToDto)
                .toList();
    }

    public AssetResdto getAssetById(long assetId) {
        Asset asset=assetRepository.findById(assetId)
                .orElseThrow(()->new ResourceNotFoundException("Asset not found with Id."));
    return AssetMapper.mapToDto(asset);
    }

    public Asset getAssetByGivenId(long assetId) {
        return assetRepository.findById(assetId)
                .orElseThrow(()->new ResourceNotFoundException("Asset Not found with id."));
    }

    public Asset updateAsset(Asset asset) {
        return assetRepository.save(asset);
    }
}

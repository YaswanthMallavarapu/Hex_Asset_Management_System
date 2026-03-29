package com.asset.demo.dto;

import com.asset.demo.enums.RequestStatus;

import java.time.Instant;

public record AssetRequestResDto(
        long id,
        long employeeId,
        String employeeName,
        long assetId,
        String assetName,
        Instant requestedDate,
        RequestStatus status
) {
}

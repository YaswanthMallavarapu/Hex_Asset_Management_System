package com.asset.demo.dto;

import com.asset.demo.enums.AssetStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AssetReqDto(
        @NotNull(message="assetNo cannot be null")
        @NotBlank(message="assetNo cannot be blank")
        String assetNo,
        @NotNull(message="assetName cannot be null")
        @NotBlank(message="assetName cannot be blank")
        String assetName,
        @NotNull(message="assetModel cannot be null")
        @NotBlank(message="assetModel cannot be blank")
        String assetModel,

        LocalDate manufacturedDate,

        BigDecimal price


) {
}

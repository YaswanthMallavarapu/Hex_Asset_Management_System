package com.asset.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Category;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AssetResdto(
        long id,

        String assetNo,

        String assetName,

        String assetModel,

        LocalDate manufacturedDate,

        long categoryId


) {
}

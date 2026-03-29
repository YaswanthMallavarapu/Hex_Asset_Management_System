package com.asset.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AssetCategoryReqDto(
        @NotNull(message="category cannot be null")
        @NotBlank(message="category cannot be blank")
        String categoryName,

        String text,

        int quantity
) {
}

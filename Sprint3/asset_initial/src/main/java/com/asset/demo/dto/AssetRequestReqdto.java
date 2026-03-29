package com.asset.demo.dto;

import jakarta.validation.constraints.NotNull;

public record AssetRequestReqdto(
        @NotNull
        String remarks
) {
}

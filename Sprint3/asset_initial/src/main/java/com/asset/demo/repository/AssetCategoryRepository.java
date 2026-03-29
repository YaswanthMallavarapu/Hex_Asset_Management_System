package com.asset.demo.repository;

import com.asset.demo.model.AssetCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetCategoryRepository extends JpaRepository<AssetCategory,Long> {
}

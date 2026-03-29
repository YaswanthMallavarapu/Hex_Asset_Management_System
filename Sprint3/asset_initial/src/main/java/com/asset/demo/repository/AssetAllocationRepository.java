package com.asset.demo.repository;

import com.asset.demo.model.AssetAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetAllocationRepository extends JpaRepository<AssetAllocation,Long> {
}

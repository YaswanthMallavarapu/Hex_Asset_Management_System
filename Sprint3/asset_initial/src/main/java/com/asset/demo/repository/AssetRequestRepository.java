package com.asset.demo.repository;

import com.asset.demo.model.AssetRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRequestRepository extends JpaRepository<AssetRequest,Long> {
}

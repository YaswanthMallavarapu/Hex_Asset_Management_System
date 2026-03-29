package com.asset.demo.repository;

import com.asset.demo.model.AssetAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetAuditRepository extends JpaRepository<AssetAudit,Long> {
}

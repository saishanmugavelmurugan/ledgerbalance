package com.zing.ledgerbalance.db.repository;

import com.zing.ledgerbalance.db.entity.LedgerEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface LedgerEntryRepository extends JpaRepository<LedgerEntryEntity, UUID> {
    List<LedgerEntryEntity>  findByAccountIdAndTimestamp(String accountId, LocalDateTime dateTime);
}

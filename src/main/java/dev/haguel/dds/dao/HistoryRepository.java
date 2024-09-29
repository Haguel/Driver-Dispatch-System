package dev.haguel.dds.dao;

import dev.haguel.dds.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}

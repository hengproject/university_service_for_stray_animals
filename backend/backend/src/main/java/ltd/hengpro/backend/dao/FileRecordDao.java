package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.FileRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRecordDao extends JpaRepository<FileRecord, String> {
}
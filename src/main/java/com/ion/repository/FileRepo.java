package com.ion.repository;

import com.ion.model.FileDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<FileDTO, Long> {
}

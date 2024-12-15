package de.malteasm.updateservice.common.firmware.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FirmwareRepository extends JpaRepository<Firmware, Integer> {
    List<Firmware> findAllByHardwareId(String hardwareId);
}

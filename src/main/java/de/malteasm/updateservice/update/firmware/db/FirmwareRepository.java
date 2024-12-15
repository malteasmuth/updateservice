package de.malteasm.updateservice.update.firmware.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FirmwareRepository extends JpaRepository<Firmware, Integer> {
    List<Firmware> findAllByHardwareId(String hardwareId);
}

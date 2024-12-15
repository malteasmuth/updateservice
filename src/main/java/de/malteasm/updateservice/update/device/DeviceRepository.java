package de.malteasm.updateservice.update.device;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, String> {
}

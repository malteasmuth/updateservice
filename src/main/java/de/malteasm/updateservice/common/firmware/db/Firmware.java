package de.malteasm.updateservice.common.firmware.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "firmware_version")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Firmware {
    @Id
    @Column(name = "version_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int versionId;

    @Column(name = "version_number")
    private String versionNumber;

    @Column(name = "download_url")
    private String downloadURL;

    @Column(name="hardware_id")
    private String hardwareId;
}

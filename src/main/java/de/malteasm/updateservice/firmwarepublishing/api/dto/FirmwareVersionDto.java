package de.malteasm.updateservice.firmwarepublishing.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FirmwareVersionDto {
    private String versionNumber; // Unique?
    private String downloadUrl;
    private String hardwareId;
}

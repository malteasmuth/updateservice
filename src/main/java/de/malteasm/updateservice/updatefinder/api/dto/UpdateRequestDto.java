package de.malteasm.updateservice.updatefinder.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateRequestDto {
    private String hardwareId;
    private String currentFirmwareVersion;
}

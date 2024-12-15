package de.malteasm.updateservice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateRequestDTO {
    private String hardwareId;
    private String currentFirmwareVersion;
}

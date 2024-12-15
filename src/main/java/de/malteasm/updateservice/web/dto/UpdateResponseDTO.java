package de.malteasm.updateservice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateResponseDTO {
    private boolean hasNewFirmwareVersion;
    private String updateURL;
    private String hardwareId;
}
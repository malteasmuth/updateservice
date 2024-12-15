package de.malteasm.updateservice.updatefinder.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInformation {
    private boolean hasNewFirmwareVersion;
    private String updateUrl;
    private String hardwareId;
}
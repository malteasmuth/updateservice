package de.malteasm.updateservice.web.dto;

import de.malteasm.updateservice.update.firmware.businessObject.Version;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FirmwareVersionDTO {
    private int versionId;
    private String versionNumber;
    private String downloadURL;
    private String hardwareId;
}

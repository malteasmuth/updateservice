package de.malteasm.updateservice.common.firmware.businessObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FirmwareBusinessObject implements Comparable<FirmwareBusinessObject> {
    private Version versionNumber;
    private String downloadURL;
    private String hardwareId;

    @Override
    public int compareTo(FirmwareBusinessObject o) {
        return this.versionNumber.compareTo(o.versionNumber);
    }
}

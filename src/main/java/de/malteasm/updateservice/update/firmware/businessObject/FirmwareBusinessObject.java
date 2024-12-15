package de.malteasm.updateservice.update.firmware.businessObject;

import de.malteasm.updateservice.update.firmware.businessObject.Version;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FirmwareBusinessObject implements Comparable<FirmwareBusinessObject> {

    private int versionId;
    private Version versionNumber;
    private String downloadURL;
    private String hardwareId;

    @Override
    public int compareTo(FirmwareBusinessObject o) {
        return this.versionNumber.compareTo(o.versionNumber);
    }
}

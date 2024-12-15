package de.malteasm.updateservice.update.firmware.businessObject;

import de.malteasm.updateservice.update.firmware.db.Firmware;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FirmwareEntityMapper {

    public FirmwareBusinessObject toBusinessObject(Firmware firmwareVersion){

        Version versionNumber = createVersionFromString(firmwareVersion.getVersionNumber());
        return new FirmwareBusinessObject(firmwareVersion.getVersionId(), versionNumber, firmwareVersion.getDownloadURL(), firmwareVersion.getHardwareId());
    }

    public Firmware toEntity(FirmwareBusinessObject firmwareBusinessObject){
        return new Firmware(firmwareBusinessObject.getVersionId(),
                firmwareBusinessObject.getVersionNumber().toString(),
                firmwareBusinessObject.getDownloadURL(),
                firmwareBusinessObject.getHardwareId());
    }

    public Version createVersionFromString(String versionNumberString){
        String[] versionNumberParts = versionNumberString.split("\\.");
        return new Version(Integer.parseInt(versionNumberParts[0]), Integer.parseInt(versionNumberParts[1]), Integer.parseInt(versionNumberParts[2]));
    }
}

package de.malteasm.updateservice.common.firmware.businessObject;

import de.malteasm.updateservice.common.firmware.db.Firmware;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FirmwareEntityMapper {

    public FirmwareBusinessObject toBusinessObject(Firmware firmwareVersion){

        Version versionNumber = createVersionFromString(firmwareVersion.getVersionNumber());
        return new FirmwareBusinessObject(versionNumber,
                firmwareVersion.getDownloadURL(),
                firmwareVersion.getHardwareId());
    }

    public Firmware toEntity(FirmwareBusinessObject firmwareBusinessObject){
        Firmware firmware = new Firmware();
        firmware.setDownloadURL(firmwareBusinessObject.getDownloadURL());
        firmware.setHardwareId(firmwareBusinessObject.getHardwareId());
        firmware.setVersionNumber(firmware.getVersionNumber());
        return firmware;
    }

    public Version createVersionFromString(String versionNumberString){
        String[] versionNumberParts = versionNumberString.split("\\.");
        return new Version(Integer.parseInt(versionNumberParts[0]), Integer.parseInt(versionNumberParts[1]), Integer.parseInt(versionNumberParts[2]));
    }
}

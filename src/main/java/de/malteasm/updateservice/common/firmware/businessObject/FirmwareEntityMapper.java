package de.malteasm.updateservice.common.firmware.businessObject;

import de.malteasm.updateservice.common.firmware.db.Firmware;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Component
@AllArgsConstructor
public class FirmwareEntityMapper {

    public FirmwareBusinessObject toBusinessObject(Firmware firmwareVersion){
        Version versionNumber = createVersionFromString(firmwareVersion.getVersionNumber());

        FirmwareBusinessObject firmware = new FirmwareBusinessObject();
        firmware.setVersionNumber(versionNumber);
        firmware.setDownloadURL(firmwareVersion.getDownloadURL());
        firmware.setHardwareId(firmwareVersion.getHardwareId());

        return firmware;
    }

    public Firmware toEntity(FirmwareBusinessObject firmwareBusinessObject){
        Firmware firmware = new Firmware();
        firmware.setDownloadURL(firmwareBusinessObject.getDownloadURL());
        firmware.setHardwareId(firmwareBusinessObject.getHardwareId());
        firmware.setVersionNumber(firmware.getVersionNumber());

        return firmware;
    }

    public Version createVersionFromString(String versionNumberString){
        Pattern pattern = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)");
        Matcher matcher = pattern.matcher(versionNumberString);

        if(!matcher.matches()){
            throw new IllegalArgumentException("No valid version number");
        }

        return new Version(Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(3)));
    }
}

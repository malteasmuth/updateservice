package de.malteasm.updateservice.firmwarepublishing;

import de.malteasm.updateservice.firmwarepublishing.api.dto.FirmwareVersionDto;

public interface PublishFirmwareService {
    FirmwareVersionDto save(FirmwareVersionDto firmware);
}

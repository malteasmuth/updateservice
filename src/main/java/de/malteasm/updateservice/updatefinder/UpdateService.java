package de.malteasm.updateservice.updatefinder;

import de.malteasm.updateservice.updatefinder.api.dto.UpdateInformation;

public interface UpdateService {

    UpdateInformation findUpdateForDevice(String hardwareId, String currentFirmwareVersion);
}

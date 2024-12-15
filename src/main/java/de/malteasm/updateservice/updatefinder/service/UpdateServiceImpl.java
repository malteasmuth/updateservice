package de.malteasm.updateservice.updatefinder.service;

import de.malteasm.updateservice.updatefinder.UpdateService;
import de.malteasm.updateservice.common.firmware.businessObject.FirmwareEntityMapper;
import de.malteasm.updateservice.common.firmware.businessObject.FirmwareBusinessObject;
import de.malteasm.updateservice.common.firmware.businessObject.Version;
import de.malteasm.updateservice.common.firmware.db.FirmwareRepository;
import de.malteasm.updateservice.updatefinder.api.dto.UpdateInformation;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class UpdateServiceImpl implements UpdateService {

    private final FirmwareRepository firmwareRepository;
    private final FirmwareEntityMapper entityMapper;

    public UpdateServiceImpl(FirmwareRepository firmwareRepository, FirmwareEntityMapper entityMapper) {
        this.firmwareRepository = firmwareRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public UpdateInformation findUpdateForDevice(String hardwareId, String versionId){
        var currentVersion = entityMapper.createVersionFromString(versionId);
        return firmwareRepository.findAllByHardwareId(hardwareId)
                .stream()
                .map(entityMapper::toBusinessObject)
                .filter(version -> isANewerVersion(version, currentVersion))
                .max(Comparator.naturalOrder())
                .map(firmware -> new UpdateInformation(true, firmware.getDownloadURL(), firmware.getHardwareId()))
                .orElseGet(() -> new UpdateInformation(false, null, hardwareId));
    }

    private static boolean isANewerVersion(FirmwareBusinessObject version, Version currentVersion) {
        return version.getVersionNumber().compareTo(currentVersion) > 0;
    }
}

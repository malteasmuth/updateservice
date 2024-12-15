package de.malteasm.updateservice.updatefinder.service;

import de.malteasm.updateservice.updatefinder.UpdateService;
import de.malteasm.updateservice.common.firmware.businessObject.FirmwareEntityMapper;
import de.malteasm.updateservice.common.firmware.businessObject.FirmwareBusinessObject;
import de.malteasm.updateservice.common.firmware.businessObject.Version;
import de.malteasm.updateservice.common.firmware.db.FirmwareRepository;
import de.malteasm.updateservice.updatefinder.api.dto.UpdateRequestDto;
import de.malteasm.updateservice.updatefinder.api.dto.UpdateResponseDto;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UpdateServiceImpl implements UpdateService {

    private final FirmwareRepository firmwareRepository;
    private final FirmwareEntityMapper entityMapper;

    public UpdateServiceImpl(FirmwareRepository firmwareRepository, FirmwareEntityMapper entityMapper) {
        this.firmwareRepository = firmwareRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public UpdateResponseDto findUpdateForDevice(UpdateRequestDto updateRequestDto){
        var currentVersion = entityMapper.createVersionFromString(updateRequestDto.getCurrentFirmwareVersion());
        return firmwareRepository.findAllByHardwareId(updateRequestDto.getHardwareId())
                .stream()
                .map(entityMapper::toBusinessObject)
                .filter(version -> isANewerVersion(version, currentVersion))
                .max(Comparator.naturalOrder())
                .map(f -> new UpdateResponseDto(true, f.getDownloadURL(), f.getHardwareId()))
                .orElseGet(() -> new UpdateResponseDto(false, null, updateRequestDto.getHardwareId()));
    }

    private static boolean isANewerVersion(FirmwareBusinessObject version, Version currentVersion) {
        return version.getVersionNumber().compareTo(currentVersion) > 0;
    }
}

package de.malteasm.updateservice.update;

import de.malteasm.updateservice.update.device.DeviceRepository;
import de.malteasm.updateservice.update.firmware.businessObject.FirmwareEntityMapper;
import de.malteasm.updateservice.update.firmware.businessObject.FirmwareBusinessObject;
import de.malteasm.updateservice.update.firmware.businessObject.Version;
import de.malteasm.updateservice.update.firmware.db.FirmwareRepository;
import de.malteasm.updateservice.web.dto.FirmwareVersionDTO;
import de.malteasm.updateservice.web.dto.UpdateRequestDTO;
import de.malteasm.updateservice.web.dto.UpdateResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UpdateService {

    private final FirmwareRepository firmwareRepository;
    private final FirmwareEntityMapper entityMapper;

    public UpdateService(DeviceRepository deviceRepository, FirmwareRepository firmwareRepository, FirmwareEntityMapper entityMapper) {
        this.firmwareRepository = firmwareRepository;
        this.entityMapper = entityMapper;
    }

    public UpdateResponseDTO findUpdateForDevice(UpdateRequestDTO updateRequestDTO){

        List<FirmwareBusinessObject> allFirmwareVersions = firmwareRepository.findAllByHardwareId(updateRequestDTO.getHardwareId())
                .stream()
                .map(entityMapper::toBusinessObject)
                .toList();

        FirmwareBusinessObject mostRecentFirmwareVersion = hasNewerFirmware(entityMapper.createVersionFromString(updateRequestDTO.getCurrentFirmwareVersion()),
                allFirmwareVersions);

        if(mostRecentFirmwareVersion == null){
            return new UpdateResponseDTO(false, null, updateRequestDTO.getHardwareId());
        }

        return new UpdateResponseDTO(true, mostRecentFirmwareVersion.getDownloadURL(), mostRecentFirmwareVersion.getHardwareId());
    }

    public FirmwareVersionDTO save(FirmwareVersionDTO firmware){

        FirmwareBusinessObject firmwareToSave = new FirmwareBusinessObject(firmware.getVersionId(), entityMapper.createVersionFromString(firmware.getVersionNumber()), firmware.getDownloadURL(), firmware.getHardwareId());
        FirmwareBusinessObject savedFirmwareVersion = entityMapper.toBusinessObject(firmwareRepository.save(entityMapper.toEntity(firmwareToSave)));
        return new FirmwareVersionDTO(savedFirmwareVersion.getVersionId(), savedFirmwareVersion.getVersionNumber().toString(), savedFirmwareVersion.getDownloadURL(), savedFirmwareVersion.getHardwareId());
    }

    private FirmwareBusinessObject hasNewerFirmware(Version currentVersion, List<FirmwareBusinessObject> availableFirmwareVersions) {
        List<FirmwareBusinessObject> newerVersions = availableFirmwareVersions.stream().filter(version -> version.getVersionNumber().compareTo(currentVersion) > 0).toList();
        return newerVersions.stream().sorted(Comparator.<FirmwareBusinessObject> reverseOrder()).findFirst().orElse(null);
    }
}

package de.malteasm.updateservice.firmwarepublishing.service;

import de.malteasm.updateservice.common.firmware.businessObject.FirmwareBusinessObject;
import de.malteasm.updateservice.common.firmware.businessObject.FirmwareEntityMapper;
import de.malteasm.updateservice.common.firmware.db.FirmwareRepository;
import de.malteasm.updateservice.firmwarepublishing.PublishFirmwareService;
import de.malteasm.updateservice.firmwarepublishing.api.dto.FirmwareVersionDto;
import org.springframework.stereotype.Service;

@Service
public class PublishFirmwareServiceImpl implements PublishFirmwareService {

    private final FirmwareEntityMapper entityMapper;
    private final FirmwareRepository firmwareRepository;

    public PublishFirmwareServiceImpl(FirmwareEntityMapper entityMapper, FirmwareRepository firmwareRepository){
        this.entityMapper = entityMapper;
        this.firmwareRepository = firmwareRepository;
    }

    @Override
    public FirmwareVersionDto save(FirmwareVersionDto firmware) {
        FirmwareBusinessObject firmwareToSave = new FirmwareBusinessObject(firmware.getVersionId(), entityMapper.createVersionFromString(firmware.getVersionNumber()), firmware.getDownloadUrl(), firmware.getHardwareId());
        FirmwareBusinessObject savedFirmwareVersion = entityMapper.toBusinessObject(firmwareRepository.save(entityMapper.toEntity(firmwareToSave)));
        return new FirmwareVersionDto(savedFirmwareVersion.getVersionId(), savedFirmwareVersion.getVersionNumber().toString(), savedFirmwareVersion.getDownloadURL(), savedFirmwareVersion.getHardwareId());
    }
}

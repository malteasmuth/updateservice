package de.malteasm.updateservice.firmwarepublishing.service;

import de.malteasm.updateservice.common.firmware.businessObject.FirmwareBusinessObject;
import de.malteasm.updateservice.common.firmware.businessObject.FirmwareEntityMapper;
import de.malteasm.updateservice.common.firmware.businessObject.Version;
import de.malteasm.updateservice.common.firmware.db.Firmware;
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
        Version versionNumber = entityMapper.createVersionFromString(firmware.getVersionNumber());

        FirmwareBusinessObject firmwareToSave = new FirmwareBusinessObject();
        firmwareToSave.setHardwareId(firmware.getHardwareId());
        firmwareToSave.setVersionNumber(versionNumber);
        firmwareToSave.setDownloadURL(firmware.getDownloadUrl());

        Firmware savedFirmware = firmwareRepository.save(entityMapper.toEntity(firmwareToSave));
        FirmwareBusinessObject firmwareBusinessObject = entityMapper.toBusinessObject(savedFirmware);

        return new FirmwareVersionDto(firmwareBusinessObject.getVersionNumber().toString(),
                firmwareBusinessObject.getDownloadURL(),
                firmwareBusinessObject.getHardwareId());
    }
}

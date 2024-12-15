package de.malteasm.updateservice.firmwarepublishing.api;

import de.malteasm.updateservice.firmwarepublishing.PublishFirmwareService;
import de.malteasm.updateservice.firmwarepublishing.api.dto.FirmwareVersionDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/firmware-versions")
public class PublishFirmwareController {

    private final PublishFirmwareService publishFirmwareService;

    public PublishFirmwareController(PublishFirmwareService publishFirmwareService){
        this.publishFirmwareService = publishFirmwareService;
    }

    @PostMapping("/create")
    public FirmwareVersionDto createFirmwareVersion(@RequestBody FirmwareVersionDto firmwareVersion) {
        return publishFirmwareService.save(firmwareVersion);
    }
}

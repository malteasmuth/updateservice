package de.malteasm.updateservice.web;

import de.malteasm.updateservice.update.UpdateService;
import de.malteasm.updateservice.update.device.Device;
import de.malteasm.updateservice.web.dto.FirmwareVersionDTO;
import de.malteasm.updateservice.web.dto.UpdateRequestDTO;
import de.malteasm.updateservice.web.dto.UpdateResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UpdateController{

    private final UpdateService updateService;

    public UpdateController(UpdateService updateService){
        this.updateService = updateService;
    }

    @GetMapping("/version-update")
    public UpdateResponseDTO getUpdate(@RequestParam String hardwareId, @RequestParam String currentFirmwareVersion){

        UpdateRequestDTO updateRequestDTO = new UpdateRequestDTO(hardwareId, currentFirmwareVersion);
        return updateService.findUpdateForDevice(updateRequestDTO);
    }

    @PostMapping("/add-firmware-version")
    public FirmwareVersionDTO createFirmwareVersion(@RequestBody FirmwareVersionDTO firmwareVersion) {
        return updateService.save(firmwareVersion);
    }
}

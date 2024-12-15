package de.malteasm.updateservice.updatefinder.api;

import de.malteasm.updateservice.updatefinder.UpdateService;
import de.malteasm.updateservice.updatefinder.api.dto.UpdateRequestDto;
import de.malteasm.updateservice.updatefinder.api.dto.UpdateResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UpdateController{

    private final UpdateService updateService;

    public UpdateController(UpdateService updateService){
        this.updateService = updateService;
    }

    @GetMapping("/version-update")
    public UpdateResponseDto getUpdate(@RequestParam String hardwareId, @RequestParam String currentFirmwareVersion){

        UpdateRequestDto updateRequestDTO = new UpdateRequestDto(hardwareId, currentFirmwareVersion);
        return updateService.findUpdateForDevice(updateRequestDTO);
    }
}

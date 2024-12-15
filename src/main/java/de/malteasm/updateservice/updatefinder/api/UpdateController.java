package de.malteasm.updateservice.updatefinder.api;

import de.malteasm.updateservice.updatefinder.UpdateService;
import de.malteasm.updateservice.updatefinder.api.dto.UpdateInformation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UpdateController{

    private final UpdateService updateService;

    public UpdateController(UpdateService updateService){
        this.updateService = updateService;
    }

    @GetMapping("/version-update")
    public UpdateInformation getUpdate(@RequestParam String hardwareId, @RequestParam String currentFirmwareVersion){
        return updateService.findUpdateForDevice(hardwareId, currentFirmwareVersion);
    }
}

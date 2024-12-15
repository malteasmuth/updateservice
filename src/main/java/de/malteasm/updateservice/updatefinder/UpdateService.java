package de.malteasm.updateservice.updatefinder;

import de.malteasm.updateservice.updatefinder.api.dto.UpdateRequestDto;
import de.malteasm.updateservice.updatefinder.api.dto.UpdateResponseDto;

public interface UpdateService {

    UpdateResponseDto findUpdateForDevice(UpdateRequestDto updateRequestDTO);
}

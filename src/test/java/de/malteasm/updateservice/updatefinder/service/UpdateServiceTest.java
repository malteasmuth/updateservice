package de.malteasm.updateservice.updatefinder.service;

import de.malteasm.updateservice.common.firmware.businessObject.FirmwareEntityMapper;
import de.malteasm.updateservice.common.firmware.db.FirmwareRepository;
import de.malteasm.updateservice.updatefinder.api.dto.UpdateInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UpdateServiceTest {

    @Autowired
    private FirmwareRepository firmwareRepository;
    @Autowired
    private FirmwareEntityMapper entityMapper;
    private UpdateServiceImpl updateService;

    @BeforeEach
    public void init(){
        this.updateService = new UpdateServiceImpl(firmwareRepository, entityMapper);
    }

    @Test
    void GIVEN_NewerVersionExistsForHardwareId_THEN_UpdateInformationContainsAllRelevantInformation(){

        String hardwareId = "NCC-74656";
        String currentVersion = "02.1.231";

        UpdateInformation result = updateService.findUpdateForDevice(hardwareId, currentVersion);

        UpdateInformation expectedResult = new UpdateInformation(true, "www.api.version/3.8.12", "NCC-74656");
        assertEquals(expectedResult, result);
    }

    @Test
    void GIVEN_NoVersionExistsForHardwareId_THEN_UpdateInformationHasFalse(){

        String hardwareId = "NCC-74656";
        String currentVersion = "27.4.21";

        UpdateInformation result = updateService.findUpdateForDevice(hardwareId, currentVersion);

        UpdateInformation exptectedResult = new UpdateInformation(false, null, "NCC-74656");
        assertEquals(exptectedResult, result);
    }

    @Test
    void GIVEN_HardwareVersionDoesNotExist_THEN_IllegalArgumentExceptionIsThrown(){

        String illegalHardwareId = "NCC-74696";
        String currentVersion = "27.4.21";

        assertThrows(IllegalArgumentException.class, () -> updateService.findUpdateForDevice(illegalHardwareId, currentVersion));
    }

    @Test
    void GIVEN_InvalidFormattedVersionNumber_THEN_IllegalArgumentExceptionIsThrown(){
        String hardwareId = "NCC-74656";
        String illegalFormattedVersion = "27.4..21";

        assertThrows(IllegalArgumentException.class, () -> updateService.findUpdateForDevice(hardwareId, illegalFormattedVersion));
    }

    @Test
    void GIVEN_VersionDoesNotContainNumbers_THEN_IllegalArgumentExceptionIsThrown(){
        String hardwareId = "NCC-74656";
        String illegalFormattedVersion = "I'm not a number";

        assertThrows(IllegalArgumentException.class, () -> updateService.findUpdateForDevice(hardwareId, illegalFormattedVersion));
    }
}

package de.malteasm.updateservice.common.device.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "device")
@NoArgsConstructor
@Data
public class Device {

    @Id
    @Column(name = "hardware_id")
    private String hardwareId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "launch_year")
    private LocalDate launch_year;
}

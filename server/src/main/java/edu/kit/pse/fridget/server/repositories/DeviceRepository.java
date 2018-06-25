package edu.kit.pse.fridget.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.kit.pse.fridget.server.models.Device;

public interface DeviceRepository extends JpaRepository<Device, String> {
}

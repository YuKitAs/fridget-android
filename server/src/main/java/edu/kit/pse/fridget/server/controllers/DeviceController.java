package edu.kit.pse.fridget.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.kit.pse.fridget.server.models.Device;
import edu.kit.pse.fridget.server.services.DeviceService;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService service;

    @Autowired
    public DeviceController(DeviceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Device> saveDevice(@RequestBody Device device) {
        return new ResponseEntity<>(service.saveDevice(device), HttpStatus.CREATED);
    }
}

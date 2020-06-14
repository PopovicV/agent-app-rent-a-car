package com.example.agentapp.controller;

import com.example.agentapp.model.Notification;
import com.example.agentapp.model.VehicleStyle;
import com.example.agentapp.service.VehicleStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("catalogue/vehicleStyle")
public class VehicleStyleController {
    @Autowired
    private VehicleStyleService vehicleStyleService;

    /**
     * GET server/catalogue/vehicleStyle/{id}
     *
     * @return return a vehicle style
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleStyle> getOneVehicleStyle(@PathVariable String id) {
        try {
            return new ResponseEntity<>(vehicleStyleService.findOneStyle(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * DELETE server/catalogue/vehicleStyle/{id}
     *
     * @return return notification
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> deleteVehicleStyle(@PathVariable String id) {
        try {
            vehicleStyleService.deleteOneStyle(id);
            return new ResponseEntity<>(new Notification("Successfully deleted vehicle style id = " + id, true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * PUT server/catalogue/vehicleStyle
     *
     * @return return status of creating a vehicle style request
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> putVehicleStyle(@PathVariable String id, @RequestBody VehicleStyle vehicleStyle) {
        try {
            vehicleStyleService.changeStyle(id, vehicleStyle);

            return new ResponseEntity<>(new Notification("Vehicle style changed to " + vehicleStyle.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    /**
     * GET server/catalogue/vehicleStyle
     *
     * @return return all vehicle styles
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getVehicleStyle() {
        List<VehicleStyle> vehicleStyleList = vehicleStyleService.getAllStyles();
        if(vehicleStyleList.size()==0) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vehicleStyleList, HttpStatus.OK);
    }

    /**
     * POST server/catalogue/vehicleStyle
     *
     * @return return status of creating vehicle style request
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> postVehicleFuelType(@RequestBody VehicleStyle vehicleStyle) {
        try {
            vehicleStyleService.addNewStyle(vehicleStyle);

            return new ResponseEntity<>(new Notification("Successfully added a vehicle style " + vehicleStyle.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }
}
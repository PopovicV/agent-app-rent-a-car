package com.example.agentapp.controller;

import com.example.agentapp.dto.RequestDTO;
import com.example.agentapp.model.Request;
import com.example.agentapp.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("request")
public class RequestController {

    @Autowired
    RequestService requestService;

    /**
     * GET /server/request
     *
     * @return return all requests
     */
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<Request>> getAll() {
        return new ResponseEntity<>(this.requestService.findAll(), HttpStatus.OK);
    }

    /**
     * PUT /server/request/{id}
     *
     * @return returns status of request update
     */
    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Request> updateRequest(@PathVariable("id") Long id, @RequestBody Request request) {
        Request newRequest = this.requestService.update(id, request);
        if (newRequest != null)
            return new ResponseEntity<>(newRequest, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    /**
     * POST /server/request/
     *
     * @return returns status of new request creation
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> newRequest(@RequestBody RequestDTO requests) {
        System.out.println(requests);
        boolean status = this.requestService.addRequest(requests);
        if (status){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * DELETE /server/request/{id}
     *
     * @return returns status of request deletion
     */
    @DeleteMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> deleteRequest(@PathVariable("id") Long id) {
        boolean status = this.requestService.delete(id);
        if (status)
            return new ResponseEntity<>("Success", HttpStatus.OK);
        else
            return new ResponseEntity<>("Request not found", HttpStatus.NOT_FOUND);
    }

    /**
     * POST /server/request/physicalRent
     *
     * @return returns status of new physical request creation
     */
    @PostMapping(value = "/physicalRent", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Boolean> physicalRenting(@RequestBody Request request) {
        System.out.println(request);
        boolean status = this.requestService.addPhysicalRenting(request);
        if (status){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

    }

//    @GetMapping(value = "/requestHistory", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<BundleDTO>> requestHistory(@RequestParam(value = "userId") Long userId, @RequestParam(value = "userType") int userType) {
//
//
//    }
}

package com.example.agentapp.controller;

import com.example.agentapp.model.JwtResponse;
import com.example.agentapp.service.CoordinateService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "token")
public class CoordinateController {

    @Autowired
    CoordinateService secretService;

    @MessageMapping("/send/message")
    public void onRecievedMessage(String message){
        System.out.println("recieved");
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> generateToken(@RequestBody Map<String, Object> claims) {
        System.out.println("Token request recieved");
        //boolean valid = service.testAuthenticate(vehicleId);
        String jws = secretService.createJWT((Integer) claims.get("id"),300000); //5 mins for simulation purposes
        return new ResponseEntity<>(new JwtResponse(jws), HttpStatus.OK);

    }


}

package com.example.agentapp.dto.request;

import com.example.agentapp.model.enums.Status;
import com.example.agentapp.model.request.Bundle;
import com.example.agentapp.model.request.Request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequestDTO {

    List<Request> requests = new ArrayList<>();
    List<Bundle> bundles = new ArrayList<>();

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public List<Bundle> getBundles() {
        return bundles;
    }

    public void setBundles(List<Bundle> bundles) {
        this.bundles = bundles;
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
                "requests=" + requests +
                ", bundles=" + bundles +
                '}';
    }
}
package com.example.agentapp.repository;
import com.example.agentapp.model.VehicleStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleStyleRepository extends JpaRepository<VehicleStyle, Long> {
    VehicleStyle findOneById(long parseLong);
}
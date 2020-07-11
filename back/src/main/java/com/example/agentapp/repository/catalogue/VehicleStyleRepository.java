package com.example.agentapp.repository.catalogue;
import com.example.agentapp.model.catalogue.VehicleStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleStyleRepository extends JpaRepository<VehicleStyle, Long> {
    VehicleStyle findOneById(long parseLong);

    VehicleStyle findByValue(String value);
}

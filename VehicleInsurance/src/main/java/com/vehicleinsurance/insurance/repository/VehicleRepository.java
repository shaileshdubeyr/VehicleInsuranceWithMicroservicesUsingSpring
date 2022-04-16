package com.vehicleinsurance.insurance.repository;

import com.vehicleinsurance.insurance.model.InsuranceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<InsuranceData,  Integer> {

}

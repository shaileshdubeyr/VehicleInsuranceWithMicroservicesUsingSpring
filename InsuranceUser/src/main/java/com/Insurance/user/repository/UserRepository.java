package com.Insurance.user.repository;

import com.Insurance.user.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <UserData,Integer>{
    UserData findByemail(String email);
}

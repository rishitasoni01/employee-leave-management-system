package com.app.add.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.add.model.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long>{

}

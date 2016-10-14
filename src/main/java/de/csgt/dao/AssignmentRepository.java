package de.csgt.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.csgt.domain.Assignment;

public interface AssignmentRepository extends CrudRepository<Assignment, Long>{
	
	List<Assignment> findAllByClosedFalse();
}
package de.csgt.service;

import de.csgt.domain.Assignment;



public interface AssignmentService {
    Iterable<Assignment> listAllAssignments();

    Assignment getAssignmentById(Long id);

    Assignment saveAssignment(Assignment product);

    void deleteAssignment(Long id);
    
	Iterable<Assignment> listAllAssignmentsByNotClosed();

}
package de.csgt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.csgt.dao.AssignmentRepository;
import de.csgt.domain.Assignment;

@Service
public class AssignmentService implements AssignmentServiceInterface {

	@Autowired
	private AssignmentRepository assignmentRepository;
	
	@Override
	public Iterable<Assignment> listAllAssignments() {
		return assignmentRepository.findAll();
	}

	@Override
	public Assignment getAssignmentById(Long id) {
		return assignmentRepository.findOne(id);
	}

	@Override
	public Assignment saveAssignment(Assignment product) {
		return assignmentRepository.save(product);
	}

	@Override
	public void deleteAssignment(Long id) {
		assignmentRepository.delete(id);
	}

	@Override
	public Iterable<Assignment> listAllAssignmentsByNotClosed() {
		return assignmentRepository.findAllByClosedFalse();
	}

}

package de.csgt.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.csgt.domain.Sell;

public interface SellRepository extends CrudRepository<Sell, Long>{
	List<Sell> findBySoldTrue();
}
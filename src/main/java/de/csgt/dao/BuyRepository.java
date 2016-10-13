package de.csgt.dao;

import org.springframework.data.repository.CrudRepository;

import de.csgt.domain.Buy;
import de.csgt.domain.Material;

public interface BuyRepository extends CrudRepository<Buy, Long>{
	
	Iterable<Buy> findBySoldFalse();
	Iterable<Buy> findAllByOrderByBroughtAtDesc();
	Iterable<Buy> findBySoldFalseAndMaterialOrderByBroughtAt(Material material);
	Iterable<Buy> findByMaterialOrderByBroughtAt(Material material);
}
package de.csgt.dao;

import org.springframework.data.repository.CrudRepository;

import de.csgt.domain.SellMaterial;

public interface SellMaterialRepository extends CrudRepository<SellMaterial, Long>{
	
}
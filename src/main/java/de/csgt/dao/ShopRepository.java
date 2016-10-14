package de.csgt.dao;

import org.springframework.data.repository.CrudRepository;

import de.csgt.domain.Shop;

public interface ShopRepository extends CrudRepository<Shop, Long>{
	
}
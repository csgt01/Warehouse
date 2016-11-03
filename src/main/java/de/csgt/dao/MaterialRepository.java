package de.csgt.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.csgt.domain.Color;
import de.csgt.domain.Material;

public interface MaterialRepository extends CrudRepository<Material, Integer>{
	List<Material> findByColor(Color color);
}
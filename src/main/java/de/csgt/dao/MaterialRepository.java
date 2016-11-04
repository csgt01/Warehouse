package de.csgt.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import de.csgt.domain.Color;
import de.csgt.domain.Material;

public interface MaterialRepository extends CrudRepository<Material, Integer>{
	Page<Material> findAll(Pageable pageable);
	List<Material> findByColor(Color color);
	Page<Material> findByColor(Color color, Pageable pageable);
	Page<Material> findByNameContaining(String name, Pageable pageable);
	Page<Material> findByColorAndNameContaining(Color color, String name, Pageable pageable);
}
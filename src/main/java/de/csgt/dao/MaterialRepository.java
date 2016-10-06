package de.csgt.dao;

import org.springframework.data.repository.CrudRepository;

import de.csgt.domain.Material;

public interface MaterialRepository extends CrudRepository<Material, Integer>{
}
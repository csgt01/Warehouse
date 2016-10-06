package de.csgt.dao;

import org.springframework.data.repository.CrudRepository;

import de.csgt.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
}
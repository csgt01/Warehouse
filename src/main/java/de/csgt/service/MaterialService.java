package de.csgt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.csgt.dao.MaterialRepository;
import de.csgt.domain.Material;

@Service
public class MaterialService implements MaterialServiceInterface {

	@Autowired
	private MaterialRepository productRepository;
	
	@Override
	public Iterable<Material> listAllMaterials() {
		return productRepository.findAll();
	}

	@Override
	public Material getMaterialById(Integer id) {
		return productRepository.findOne(id);
	}

	@Override
	public Material saveMaterial(Material material) {
		return productRepository.save(material);
	}

	@Override
	public void deleteMaterial(Integer id) {
		productRepository.delete(id);
	}

}

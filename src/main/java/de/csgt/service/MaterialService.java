package de.csgt.service;

import java.util.List;

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

	@Override
	public void saveMaterials(List<Material> materials) {
		for (Material material : materials) {
			Material mat = getMaterialById(material.getId());
			if (mat == null) {
				saveMaterial(material);
			}
		}
		
	}

}

package de.csgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import de.csgt.dao.MaterialRepository;
import de.csgt.domain.Material;
import de.csgt.domain.Search;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private MaterialRepository materialRepository;
	
	@Override
	public Iterable<Material> listAllMaterials() {
		return materialRepository.findAll();
	}

	@Override
	public Material getMaterialById(Integer id) {
		return materialRepository.findOne(id);
	}

	@Override
	public Material saveMaterial(Material material) {
		return materialRepository.save(material);
	}

	@Override
	public void deleteMaterial(Integer id) {
		materialRepository.delete(id);
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

	@Override
	public Iterable<Material> listAllMaterials(Search search) {
		return materialRepository.findByColor(search.getColor());
	}

	@Override
	public Page<Material> listAllMaterialsPage(PageRequest pageable) {
		Page<Material> findAll = materialRepository.findAll(pageable);
		return findAll;
	}

	@Override
	public Page<Material> listAllMaterialsPage(PageRequest pageable, Search search) {
		Page<Material> findByColor = null;
		if (search.getColor() != null) {
			if (search.getSearch() != null && !search.getSearch().isEmpty()) {
				findByColor = materialRepository.findByColorAndNameContaining(search.getColor(), search.getSearch(), pageable);
			} else {
				findByColor = materialRepository.findByColor(search.getColor(), pageable);
			}
		} else {
			if (search.getSearch() != null && !search.getSearch().isEmpty()) {
				findByColor = materialRepository.findByNameContaining(search.getSearch(), pageable);
			}
		}
		
		return findByColor;
	}

}

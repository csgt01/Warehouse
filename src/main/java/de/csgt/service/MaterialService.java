package de.csgt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import de.csgt.domain.Color;
import de.csgt.domain.Material;
import de.csgt.domain.Search;



public interface MaterialService {
	Iterable<Material> listAllMaterials();
	
	Page<Material> listAllMaterialsPage(PageRequest pageable);
	Page<Material> listAllMaterialsPage(PageRequest pageable, Search search);

    Material getMaterialById(Integer id);

    Material saveMaterial(Material material);

    void deleteMaterial(Integer id);

	void saveMaterials(List<Material> materials);

	Iterable<Material> listAllMaterials(Search search);
	
	List<Material> findByNameAndColorAndSize(String name, Color color, Double size);

}
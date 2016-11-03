package de.csgt.service;

import java.util.List;

import de.csgt.domain.Material;
import de.csgt.domain.Search;



public interface MaterialService {
    Iterable<Material> listAllMaterials();

    Material getMaterialById(Integer id);

    Material saveMaterial(Material material);

    void deleteMaterial(Integer id);

	void saveMaterials(List<Material> materials);

	Iterable<Material> listAllMaterials(Search search);
}
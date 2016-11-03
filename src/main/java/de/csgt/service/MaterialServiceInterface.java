package de.csgt.service;

import java.util.List;

import de.csgt.domain.Material;



public interface MaterialServiceInterface {
    Iterable<Material> listAllMaterials();

    Material getMaterialById(Integer id);

    Material saveMaterial(Material material);

    void deleteMaterial(Integer id);

	void saveMaterials(List<Material> materials);
}
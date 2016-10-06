package de.csgt.service;

import de.csgt.domain.Material;



public interface MaterialServiceInterface {
    Iterable<Material> listAllMaterials();

    Material getMaterialById(Integer id);

    Material saveMaterial(Material product);

    void deleteMaterial(Integer id);
}
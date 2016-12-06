package de.csgt.service;

import de.csgt.domain.Buy;
import de.csgt.domain.Material;



public interface BuyService {
    Iterable<Buy> listAllBuys();

    Buy getBuyById(Long id);

    Buy saveBuy(Buy buy);

    void deleteBuy(Long id);
    
	Iterable<Buy> listAllBuysByMaterialAndNotSold(Material material);

	Iterable<Buy> listAllBuysByMaterial(Material material);

	Buy saveBuyRaw(Buy buy);
}
package de.csgt.service;

import de.csgt.domain.Buy;
import de.csgt.domain.Material;



public interface BuyServiceInterface {
    Iterable<Buy> listAllBuys();

    Buy getBuyById(Long id);

    Buy saveBuy(Buy product);

    void deleteBuy(Long id);
    
	Iterable<Buy> listAllBuysByMaterialAndNotSold(Material material);
}
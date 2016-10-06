package de.csgt.service;

import de.csgt.domain.Buy;



public interface BuyServiceInterface {
    Iterable<Buy> listAllBuys();

    Buy getBuyById(Long id);

    Buy saveBuy(Buy product);

    void deleteBuy(Long id);
}
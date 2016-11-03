package de.csgt.service;

import de.csgt.domain.Sell;



public interface SellService {
    Iterable<Sell> listAllSells();

    Sell getSellById(Long id);

    Sell saveSell(Sell product);

    void deleteSell(Long id);
}
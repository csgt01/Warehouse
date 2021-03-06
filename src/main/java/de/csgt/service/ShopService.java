package de.csgt.service;

import de.csgt.domain.Shop;



public interface ShopService {
    Iterable<Shop> listAllShops();

    Shop getShopById(Long id);

    Shop saveShop(Shop product);

    void deleteShop(Long id);
    
}
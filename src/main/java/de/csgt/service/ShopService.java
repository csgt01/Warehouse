package de.csgt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.csgt.dao.ShopRepository;
import de.csgt.domain.Shop;

@Service
public class ShopService implements ShopServiceInterface {

	@Autowired
	private ShopRepository shopRepository;
	
	@Override
	public Iterable<Shop> listAllShops() {
		return shopRepository.findAll();
	}

	@Override
	public Shop getShopById(Long id) {
		return shopRepository.findOne(id);
	}

	@Override
	public Shop saveShop(Shop product) {
		return shopRepository.save(product);
	}

	@Override
	public void deleteShop(Long id) {
		shopRepository.delete(id);
	}

}

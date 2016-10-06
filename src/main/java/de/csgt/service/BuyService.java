package de.csgt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.csgt.dao.BuyRepository;
import de.csgt.domain.Buy;

@Service
public class BuyService implements BuyServiceInterface {

	@Autowired
	private BuyRepository productRepository;
	
	@Override
	public Iterable<Buy> listAllBuys() {
		return productRepository.findAll();
	}

	@Override
	public Buy getBuyById(Long id) {
		return productRepository.findOne(id);
	}

	@Override
	public Buy saveBuy(Buy product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteBuy(Long id) {
		productRepository.delete(id);
	}

}

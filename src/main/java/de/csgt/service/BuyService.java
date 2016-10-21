package de.csgt.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.csgt.dao.BuyRepository;
import de.csgt.domain.Buy;
import de.csgt.domain.Material;

@Service
public class BuyService implements BuyServiceInterface {

	@Autowired
	private BuyRepository buyRepository;
	
	@Autowired
	private MaterialServiceInterface materialService;
	
	@Override
	public Iterable<Buy> listAllBuys() {
		Iterable<Buy> findAll = buyRepository.findAllByOrderByBroughtAtDesc();
		return findAll != null ? findAll : new ArrayList<Buy>();
	}

	@Override
	public Buy getBuyById(Long id) {
		return buyRepository.findOne(id);
	}

	@Override
	public Buy saveBuy(Buy product) {
		return buyRepository.save(product);
	}

	@Override
	@Transactional
	public void deleteBuy(Long id) {
		Buy buy = getBuyById(id);
		Material material = materialService.getMaterialById(buy.getMaterial().getId());
		material.setAvailable(material.getAvailable() + buy.getSoldInt() - buy.getQuantity());
		buyRepository.delete(id);
	}

	@Override
	public Iterable<Buy> listAllBuysByMaterialAndNotSold(Material material) {
		Iterable<Buy> findByNotSold = buyRepository.findBySoldFalseAndMaterialOrderByBroughtAt(material);
		
		return findByNotSold;
	}

	@Override
	public Iterable<Buy> listAllBuysByMaterial(Material material) {
		Iterable<Buy> findByNotSold = buyRepository.findByMaterialOrderByBroughtAt(material);
		
		return findByNotSold;
	}
	
}

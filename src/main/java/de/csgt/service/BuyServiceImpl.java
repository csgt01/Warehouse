package de.csgt.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.csgt.dao.BuyRepository;
import de.csgt.domain.Assignment;
import de.csgt.domain.Buy;
import de.csgt.domain.Material;

@Service
public class BuyServiceImpl implements BuyService {

	@Autowired
	private BuyRepository buyRepository;
	
	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private AssignmentService assingmentService;
	
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
	public Buy saveBuy(Buy buy) {
		Assignment ass = assingmentService.getAssignmentById(buy.getAssignment().getId());
        buy.setBroughtAt(ass.getOrderedAt());
        Material mat = buy.getMaterial();
        mat.setAvailable(mat.getAvailable() - buy.getTempQuantity() + buy.getQuantity());
        Material saveMaterial = materialService.saveMaterial(mat);
        buy.setMaterial(saveMaterial);
		return buyRepository.save(buy);
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

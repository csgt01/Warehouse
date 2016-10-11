package de.csgt.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.csgt.dao.SellRepository;
import de.csgt.domain.Sell;

@Service
public class SellService implements SellServiceInterface {

	@Autowired
	private SellRepository sellRepository;
	
	@Override
	public Iterable<Sell> listAllSells() {
		Iterable<Sell> findAll = sellRepository.findAll();
		return findAll != null ? findAll : new ArrayList<Sell>();
	}

	@Override
	public Sell getSellById(Long id) {
		return sellRepository.findOne(id);
	}

	@Override
	public Sell saveSell(Sell product) {
		return sellRepository.save(product);
	}

	@Override
	public void deleteSell(Long id) {
		sellRepository.delete(id);
	}

}

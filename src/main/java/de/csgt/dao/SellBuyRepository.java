package de.csgt.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.csgt.domain.Sell;
import de.csgt.domain.SellBuy;

public interface SellBuyRepository extends CrudRepository<SellBuy, Long>{
	
	List<SellBuy> findBySell(Sell sell);
}
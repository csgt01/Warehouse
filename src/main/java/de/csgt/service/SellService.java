package de.csgt.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.csgt.ProductLoader;
import de.csgt.dao.SellRepository;
import de.csgt.domain.Buy;
import de.csgt.domain.Material;
import de.csgt.domain.Sell;
import de.csgt.domain.SellMaterial;

@Service
public class SellService implements SellServiceInterface {

	@Autowired
	private SellRepository sellRepository;
	@Autowired
	private MaterialService materialService;
	@Autowired
	private BuyService buyService;
	private Logger log = Logger.getLogger(SellService.class);
	
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
	@Transactional
	public Sell saveSell(Sell sell) {
		for (SellMaterial sellMat : sell.getSellMaterials()) {
			Material material = sellMat.getMaterial();
			int quantity = sellMat.getQuantity();
			int tempQuantity = quantity;
			log.info("quantity" + quantity);
			log.info("tempQuantity" + tempQuantity);
			List<Buy> listAllBuysByMaterialAndNotSold = (List<Buy>) buyService.listAllBuysByMaterialAndNotSold(material);
			for (Buy buy : listAllBuysByMaterialAndNotSold) {
				log.info("buy:" + buy.getId());
				log.info("buy.getSoldInt()" + buy.getSoldInt());
				log.info("quantity" + quantity);
				log.info("tempQuantity" + tempQuantity);
				int buyQuantity = buy.getQuantity() - buy.getSoldInt();
				if (buyQuantity > tempQuantity) {
					log.info("buyQuantity > tempQuantity");
					buy.setSoldInt(buy.getSoldInt() + tempQuantity);
					break;
				} else if (buyQuantity == tempQuantity) {
					log.info("buyQuantity == tempQuantity");
					buy.setSold(true);
					buy.setSoldInt(buy.getSoldInt() + tempQuantity);
					break;
				} else {
					log.info("buyQuantity < tempQuantity");
					int toSell = buy.getQuantity() - buy.getSoldInt();
					tempQuantity = tempQuantity - toSell;
					buy.setSold(true);
					buy.setSoldInt(buy.getQuantity());
				}
				Buy saveBuy = buyService.saveBuy(buy);
			} 
			material.setAvailable(material.getAvailable() - quantity);
			materialService.saveMaterial(material);
			
		}
		return sellRepository.save(sell);
	}

	@Override
	public void deleteSell(Long id) {
		sellRepository.delete(id);
	}

}

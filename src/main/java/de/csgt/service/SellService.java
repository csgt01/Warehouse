package de.csgt.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.csgt.dao.SellRepository;
import de.csgt.domain.Buy;
import de.csgt.domain.Material;
import de.csgt.domain.Sell;
import de.csgt.domain.SellBuy;
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
			sellMat.setSell(sell);
			Material material = sellMat.getMaterial();
			int quantity = sellMat.getQuantity();
			int tempQuantity = quantity;
			log.info("quantity" + quantity);
			log.info("tempQuantity" + tempQuantity);
			List<Buy> listAllBuysByMaterialAndNotSold = (List<Buy>) buyService.listAllBuysByMaterialAndNotSold(material);
			if (sell.getSellBuys() != null && sell.getSellBuys().size() > 0) {
				log.info("sell.getSellBuys() != null && sell.getSellBuys().size() > 0" );
				for (SellBuy sellBuy : sell.getSellBuys()) {
					Buy thisBuy = sellBuy.getBuy();
					thisBuy.setSoldInt(thisBuy.getSoldInt() - sellBuy.getQuantity());
					thisBuy.setSold(false);
					Material material2 = thisBuy.getMaterial();
					material2.setAvailable(material2.getAvailable() + sellBuy.getQuantity());
					materialService.saveMaterial(material2);
					buyService.saveBuy(thisBuy);
				} 
			}
			log.info("material:" + material);
			log.info("material:" + material.getId());
			material = materialService.getMaterialById(material.getId());
			sell.setSellBuys(new ArrayList<SellBuy>());
			for (Buy buy : listAllBuysByMaterialAndNotSold) {
				log.info("buy:" + buy.getId());
				log.info("buy.getSoldInt()" + buy.getSoldInt());
				log.info("buy.getQuantity()" + buy.getQuantity());
				log.info("quantity" + quantity);
				log.info("tempQuantity" + tempQuantity);
				int buyQuantity = buy.getQuantity() - buy.getSoldInt();
				log.info("buyQuantity:" + buyQuantity);
				if (buyQuantity > tempQuantity) {
					log.info("buyQuantity > tempQuantity");
					buy.setSold(false);
					buy.setSoldInt(buy.getSoldInt() + tempQuantity);
					sell.setTotalCosts(sell.getTotalCosts() + (tempQuantity * buy.getPrice()));
					buy = buyService.saveBuy(buy);
					sell.getSellBuys().add(new SellBuy(sell, buy, tempQuantity));
					break;
				} else if (buyQuantity == tempQuantity) {
					log.info("buyQuantity == tempQuantity");
					buy.setSold(true);
					buy.setSoldInt(buy.getSoldInt() + tempQuantity);
					sell.setTotalCosts(sell.getTotalCosts() + (tempQuantity * buy.getPrice()));
					sell.getSellBuys().add(new SellBuy(sell, buy, tempQuantity));
					buyService.saveBuy(buy);
					break;
				} else {
					log.info("buyQuantity < tempQuantity");
					int toSell = buy.getQuantity() - buy.getSoldInt();
					tempQuantity = tempQuantity - toSell;
					buy.setSold(true);
					buy.setSoldInt(buy.getQuantity());
					sell.setTotalCosts(sell.getTotalCosts() + (toSell * buy.getPrice()));
					sell.getSellBuys().add(new SellBuy(sell, buy, toSell));
					buyService.saveBuy(buy);
				}
				
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

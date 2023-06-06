package org.java.demo.pizzeria.service;

import java.util.List;
import java.util.Optional;

import org.java.demo.pizzeria.pojo.SpecialOffer;
import org.java.demo.pizzeria.repo.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService {

	
	@Autowired
	private SpecialOfferRepository specialOfferRepository;
	
	public List<SpecialOffer> findAll() {
		
		return specialOfferRepository.findAll();
	}
	public SpecialOffer save(SpecialOffer specialOffer) {
		
		return specialOfferRepository.save(specialOffer);
	}
	public Optional<SpecialOffer> findById(Integer id) {
		
		return specialOfferRepository.findById(id);
	}
	
	public void delete(SpecialOffer specialOffer) {
		specialOfferRepository.delete(specialOffer);
	}
}

package com.jackrkern.groceriesbyrecipe.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jackrkern.groceriesbyrecipe.models.Aisle;
import com.jackrkern.groceriesbyrecipe.repositories.AisleRepository;

/* @author "Jack Kern" */

@Service
public class AisleService
{
	private final AisleRepository aisleRepository;

	public AisleService(AisleRepository aisleRepository)
	{
		this.aisleRepository = aisleRepository;
	}

	public List<Aisle> getAisles()
	{
		Iterable<Aisle> aisles = aisleRepository.findAll();
		List<Aisle> aisleList = new ArrayList<>();
		aisles.forEach(aisle -> aisleList.add(aisle));
		aisleList.sort(new Comparator<Aisle>()
		{
			@Override
			public int compare(Aisle o1, Aisle o2)
			{
				return o1.getName().compareTo(o2.getName());
			}
		});
		return aisleList;
	}
}
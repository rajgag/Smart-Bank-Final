package com.example.Repository;

import java.util.List;

import com.example.model.CorporateModel;

public interface CorporateRepository {

	int addCorporate(CorporateModel corpmodel);

	CorporateModel getEmpById(int id);

	int modify(CorporateModel cmodel);
	int delete(int id);

	List<CorporateModel> viewAll();
}

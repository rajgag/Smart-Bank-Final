package com.example.Repository;

import java.util.List;

import com.example.model.AccountModel;

public interface AccountRepository {
	
	int addAccount(AccountModel acc);
	List<AccountModel> viewAll(int id);
	
	int delete(int cid,String userid);
	List<AccountModel> viewDetails(int accno);
	

}

package com.example.Repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.AccountModel;
import com.example.model.CorporateModel;

@Repository
public class AccountRepositoryImp implements AccountRepository {

	@Autowired
	DataSource dataSource;
	
	@Override
	public int addAccount(AccountModel acc) {
		
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		int i=insert.update("insert into accounts(CorporateId,AccountNo,AccountName,AccountBranch,Currency,AvailableBalance) values(?,?,?,?,?,?)",new Object[] {
			acc.getCorporateId(),acc.getAccountNo(),acc.getAccountName(),acc.getAccountBranch(),acc.getCurrency(),acc.getAvailableBalance()	
		});
		return i;
	}

	@Override
	public List<AccountModel> viewAll(int id) {
		
		String str="Yes";
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		String sql = "SELECT * from accounts where CorporateId=? and AccountStatus=?";
		
		List<AccountModel> employees  = insert.query(sql,new BeanPropertyRowMapper<AccountModel>(AccountModel.class),new Object[] {id,str});
		return employees;
	}

	@Override
	public int delete(int cid, String accname) {
	
		String str="No";
		JdbcTemplate insert =new JdbcTemplate(dataSource);
		String sql="update accounts set AccountStatus=? where CorporateId=? and AccountName=?";
		
		int i=insert.update(sql, new Object[] {str,cid,accname});
		
		return i;
	}

	@Override
	public List<AccountModel> viewDetails(int accno) {
		
		
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		String sql = "SELECT * from accounts where AccountNo=?";
		
		List<AccountModel> employees  = insert.query(sql,new BeanPropertyRowMapper<AccountModel>(AccountModel.class),new Object[] {accno});
		return employees;
	}
	
	

}

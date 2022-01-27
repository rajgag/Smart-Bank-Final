package com.example.Repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.example.model.CorporateModel;

@Repository
public class CorporateRepositoryImp implements CorporateRepository {

	@Autowired
	DataSource dataSource;

	@Override
	public int addCorporate(CorporateModel corpmodel) {

		JdbcTemplate insert=new JdbcTemplate(dataSource);
		String sql = "INSERT INTO corporate " + "(CorporateName,CorporateAdd,CorporatePhno) VALUES (?,?,?)";
		int i=insert.update(sql, new Object[] { corpmodel.getCorporateName(), corpmodel.getCorporateAdd(),corpmodel.getCorporatePhno()});
		return i;
	}
	
	@Override
	public CorporateModel getEmpById(int id){    
		JdbcTemplate obj = new JdbcTemplate(dataSource);
	    String sql="select * from corporate where CorporateId=?";    
	    CorporateModel emp= obj.queryForObject(sql,new BeanPropertyRowMapper<CorporateModel>(CorporateModel.class),new Object[]{id});  
	    return emp;
	  
	    
	}
	
	@Override
	public int modify(CorporateModel cmodel) {
		
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		int i=	insert.update("update corporate set CorporatePhno=?,CorporateAdd=? where CorporateId=?", new Object[] {
				cmodel.getCorporatePhno(),cmodel.getCorporateAdd(),cmodel.getCorporateId()
		});
		    return i;	
	} 
	
	@Override
	public List<CorporateModel> viewAll()
	{
		String str="Yes";
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		String sql = "SELECT * from corporate where status=?";
		
		List<CorporateModel> employees  = insert.query(sql,new BeanPropertyRowMapper<CorporateModel>(CorporateModel.class),str);
		return employees;

   }
	@Override
	public int delete(int id)
	{
		String str="No";
		JdbcTemplate insert=new JdbcTemplate(dataSource);
		String sql = "update corporate set status=? where CorporateId=?";
		int i=insert.update(sql, new Object[] {str,id});
		return i;
	}

}

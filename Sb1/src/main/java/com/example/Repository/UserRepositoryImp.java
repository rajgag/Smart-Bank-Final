package com.example.Repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.example.model.CorporateModel;
import com.example.model.UserModel;

@Repository
public class UserRepositoryImp implements UserRepository {

	@Autowired
	DataSource dataSource;

	@Override
	public int signin(String username, String password, UserModel userModel) {

		JdbcTemplate search = new JdbcTemplate(dataSource);
		String str1="No";
		String sql = "SELECT * from users where UserLoginId=? and UserPassword=? and UserStatus=?";
		int i = 0;
		String  str="Yes";
		String name="Alan";
		SqlRowSet usermodel = search.queryForRowSet(sql,username,password,str);

		while (usermodel.next()) {
			if (name.equals(usermodel.getString("UserLoginId"))
					&& password.equals(usermodel.getString("UserPassword"))&&str.equals(usermodel.getString("PassChanged"))) {
				i = 3;
			}
			else if (username.equals(usermodel.getString("UserLoginId"))
					&& password.equals(usermodel.getString("UserPassword"))&&str.equals(usermodel.getString("PassChanged"))) {
				i = 1;
			}
			else if(username.equals(usermodel.getString("UserLoginId"))
					&& password.equals(usermodel.getString("UserPassword"))&&str1.equals(usermodel.getString("PassChanged"))) {
				i=2;
			}

		}

		return i;
	}

	@Override
	public int addUser(UserModel usermodel) {

		JdbcTemplate insert = new JdbcTemplate(dataSource);
		String sql = "INSERT INTO users " + "(UserLoginId,UserPassword,UserAdd,UserDept,UserPhno,CorporateId)  VALUES (?,?,?,?,?,?)";
		int i = insert.update(sql, new Object[] { usermodel.getUserLoginId(), usermodel.getUserPassword(),usermodel.getUserAdd(),
				usermodel.getUserDept(),usermodel.getUserPhno(),usermodel.getCorporateId() });
		return i;
	}

	@Override
	public UserModel getuserByuserId(String uid) {
		JdbcTemplate obj = new JdbcTemplate(dataSource);
		String sql = "select * from users where UserLoginId=?";
		UserModel emp = obj.queryForObject(sql, new BeanPropertyRowMapper<UserModel>(UserModel.class),
				new Object[] {uid});
		return emp;

	}
	
	@Override
	public UserModel getuserById(int id) {
		JdbcTemplate obj = new JdbcTemplate(dataSource);
		String sql = "select * from users where CorporateId=?";
		UserModel emp = obj.queryForObject(sql, new BeanPropertyRowMapper<UserModel>(UserModel.class),
				new Object[] {id});
		return emp;

	}

	@Override
	public int modify(UserModel usermodel) {
		
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		int i = insert.update("update users set UserAdd=?,UserPhno=?,UserDept=? where UserLoginId=?",
				new Object[] { usermodel.getUserAdd(), usermodel.getUserPhno(), usermodel.getUserDept(),usermodel.getUserLoginId()});
		System.out.println(i);
		return i;
	}

	@Override
	public List<UserModel> viewAll(int id) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		String sts="Yes";
		String sql = "SELECT * from users where CorporateId=? and UserStatus=?";

		List<UserModel> employees = insert.query(sql,
				new BeanPropertyRowMapper<UserModel>(UserModel.class),new Object[]{id,sts});
		return employees;

	}

	@Override
	public int delete(String id) {
		String data="No";
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		String sql = "update users set UserStatus=? where UserLoginId=?";
		int i = insert.update(sql, new Object[] {data,id});
		return i;
	}

	@Override
	public int findCorpId(String username) {
		JdbcTemplate insert =new JdbcTemplate(dataSource);
		String corporateId="";
		 SqlRowSet  corpId=insert.queryForRowSet("select CorporateId from users where UserLoginId=?",username);
		 
		 while (corpId.next()) {
			 
					corporateId=corpId.getString("CorporateId");
					System.out.println(corporateId);
				}

		 int id=Integer.valueOf(corporateId);
		return id;
	}

	@Override
	public int changePassword(UserModel userm,String userid) {
		
		JdbcTemplate change=new JdbcTemplate(dataSource);
		String str="Yes";
		String sql="update users set PassChanged=?,UserPassword=? where UserLoginId=?";
		int i=change.update(sql, new Object[] {str,userm.getUserPassword(),userid});
		return i;
	}

}

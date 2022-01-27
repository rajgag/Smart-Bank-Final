package com.example.Repository;

import java.util.List;

import com.example.model.UserModel;

public interface UserRepository {

	int signin(String username, String password, UserModel userModel);

	UserModel getuserByuserId(String uid);

	int modify(UserModel cmodel);

	List<UserModel> viewAll(int id);

	int delete(String id);

	int addUser(UserModel corpmodel);

	UserModel getuserById(int id);

	int findCorpId(String username);

	int changePassword(UserModel userm,String userid);
}

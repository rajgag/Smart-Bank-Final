package com.example;

import java.util.List;

import javax.swing.plaf.synth.SynthStyleFactory;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.Repository.CorporateRepository;
import com.example.Repository.UserRepository;
import com.example.model.CorporateModel;
import com.example.model.UserModel;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CorporateRepository coImp;
	
	@RequestMapping("/userListPage")
	public String userlist(@RequestParam("corporateId") int cid ,Model m)
	{
		List<UserModel> userlist= userRepo.viewAll(cid);
		m.addAttribute("list",userlist);
		return "userList";
	}
	
	
	
	@RequestMapping("/userPage")
	public String userPage(Model m) {
		List<CorporateModel> userlist= coImp.viewAll();
		m.addAttribute("userlist",userlist);
		return "userSetup";
	}
	
	@PostMapping("/addUser")
	public String addUser(@Valid @ModelAttribute("user") UserModel usermodel,BindingResult bid,Model m)
	{
		
		if (!(bid.hasErrors()))
		{
			int i= userRepo.addUser(usermodel);
			if(i>0)
				return "redirect:/userPage";
			else
				return "addUser";
		}
		else
			return "addUser";
	}

	
	
	@RequestMapping("/addUserPage")
	public String addUserPage(Model m,@RequestParam("corporateId") int id) {
		
		UserModel userm=new UserModel();
		userm.setCorporateId(id);
		m.addAttribute("user", userm);
		return "addUser";
	} 
	
	
	@RequestMapping("/userModifyPage")
	public String modify(Model m,@RequestParam("uid") String uid,@RequestParam("cid") int id)
	{
		UserModel model=userRepo.getuserByuserId(uid);
		m.addAttribute("cid",id);
		m.addAttribute("user",model);
		return "Umodify";
		
	}
	
	@RequestMapping("/userModify")
	public String modifyLogic(@ModelAttribute("user") UserModel usermodel,@RequestParam("corporateId") int id,Model m)
	{
		int i=userRepo.modify(usermodel);
		
		if(i>0)
			return "redirect:/userListPage"+id;
		else
			return "Umodify";
	}
	
	@RequestMapping("/userListPage{id}")
	public String userlistonSbt(@PathVariable("id") int cid ,Model m)
	{
		List<UserModel> userlist= userRepo.viewAll(cid);
		m.addAttribute("list",userlist);
		return "userList";
	}
	
	@RequestMapping("/userDelete")
	public String delete(@RequestParam("uid") String uid,@RequestParam("cid") String cid)
	{
		int i=userRepo.delete(uid);
		if(i>0)
			return "redirect:/userDelete"+cid;
		else
			return "";
	}
	
	@RequestMapping("/userDelete{id}")
	public String deleteuserbtn(@PathVariable("id") int cid ,Model m)
	{
		List<UserModel> userlist= userRepo.viewAll(cid);
		m.addAttribute("list",userlist);
		return "userList";
	}

}

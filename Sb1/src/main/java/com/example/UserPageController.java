package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Repository.AccountRepository;
import com.example.Repository.UserRepository;
import com.example.model.AccountModel;
import com.example.model.CorporateModel;
import com.example.model.UserModel;

@Controller
public class UserPageController {

	@Autowired
	AccountRepository accImp;

	@Autowired
	UserRepository urImp;

	@RequestMapping("/userHomePage/{id}/{userid}")
	public String userPage(Model m, @PathVariable("id") int cid, @PathVariable("userid") String uid) {
		List<AccountModel> list = accImp.viewAll(cid);
		m.addAttribute("list", list);
		m.addAttribute("cid", cid);
		m.addAttribute("uid", uid);
		return "userPage";
	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public String userLogin(@RequestParam("userLoginId") String username, @RequestParam("userPassword") String password,
			@ModelAttribute("user") UserModel userm) {
		int i = urImp.signin(username, password, userm);
		
		int corpId = urImp.findCorpId(username);

		if (i == 1) {
			return "redirect:/userHomePage/"+corpId+"/"+username;
		}else if(i==2)
			return "redirect:/changePassword/"+corpId+"/"+username;
		else
			return "redirect:/home";

	}
	
	@RequestMapping("/accDetails")
	public String viewAccDetails(@RequestParam("accno") int accno,@RequestParam("uid") String id,@RequestParam("cid") int cid,Model m)
	{
		List<AccountModel> list=accImp.viewDetails(accno);
		m.addAttribute("list",list);
		m.addAttribute("acno",accno);
		m.addAttribute("cid",cid);
		m.addAttribute("uid",id);
		return "accDetailPage";
		
	}
	
	@RequestMapping("/changePassword/{id}/{userid}")
	public String chngePassword(@PathVariable("id") int cid,@PathVariable("userid") String uid,Model m)
	{
		m.addAttribute("cid",cid);
		m.addAttribute("uid",uid);
		m.addAttribute("user", new UserModel());
		return "changePassword";
		
	}
	
	@RequestMapping(value = "/userLogin1", method = RequestMethod.POST)
	public String userLogin1(@ModelAttribute("user") UserModel userm,@RequestParam("userLoginId") String uid) {
		int i = urImp.changePassword(userm,uid);
		System.out.println(i);
		int corpId = urImp.findCorpId(uid);

		if (i == 1)
			return "redirect:/userHomePage/"+corpId+"/"+uid;
		else
			return "redirect:/home";

	}
	
	
}

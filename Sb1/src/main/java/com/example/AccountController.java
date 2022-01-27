package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Repository.AccountRepository;
import com.example.Repository.CorporateRepository;
import com.example.model.AccountModel;
import com.example.model.CorporateModel;

@Controller
public class AccountController {

	@Autowired
	CorporateRepository coImp;
	
	@Autowired
	AccountRepository accImp;
	
	@RequestMapping("/accountPage")
	public String accPage(Model m) {
		List<CorporateModel> list=coImp.viewAll();  
		m.addAttribute("list",list);
		return "accountSetup";
	}
	
	@RequestMapping("/addAccount{id}")
	public String addAccPage(Model m,@PathVariable("id") int cid)
	{
		AccountModel accmodel=new AccountModel();
		accmodel.setCorporateId(cid);
		m.addAttribute("cid",cid);
		m.addAttribute("account",accmodel);
		return "addAccount";
	}
	
	@PostMapping("/addAccount")
	public String addAccLogic(@ModelAttribute("account") AccountModel acc,@RequestParam("corporateId") int id)
	{
		
		int i=accImp.addAccount(acc);
		if(i>0)
			return "redirect:/accList"+id;
		else
			return "";
	}
	
	@RequestMapping("/acclist")
	public String accList(Model m,@RequestParam("id") int cid) {
		List<AccountModel> list=accImp.viewAll(cid);  
		m.addAttribute("list",list);
		m.addAttribute("cid",cid);
		return "accList";
	}
	@RequestMapping("/accList{id}")
	public String addAccBack(@PathVariable("id") int cid,Model m)
	{
		List<AccountModel> list=accImp.viewAll(cid);  
		m.addAttribute("list",list);
		m.addAttribute("cid",cid);
		return "accList";
	}
	
	@RequestMapping("/accDelete")
	public String deleteAcc(@RequestParam("cid") int cid,@RequestParam("accname") String accname)
	{
		int i=accImp.delete(cid, accname);
		if(i>0)
			return "redirect:/accList"+cid;
		else
			return "";
	}
	
	
	
	
}

package com.example;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.Repository.CorporateRepositoryImp;
import com.example.Repository.UserRepository;
import com.example.Repository.UserRepositoryImp;
import com.example.model.CorporateModel;
import com.example.model.UserModel;

@Controller
public class MainController {

	@Autowired
	UserRepositoryImp urImp;
	@Autowired
	CorporateRepositoryImp coImp;

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String Login(@RequestParam("userLoginId") String username, @RequestParam("userPassword") String password,
			@Valid @ModelAttribute("user") UserModel userm, BindingResult bid,Model m) {
		
		int i = urImp.signin(username, password, userm);

		if (i == 3 && !(bid.hasErrors())) {
			List<CorporateModel> list = coImp.viewAll();
			m.addAttribute("list", list);
			return "redirect:/adminPage";
		} else
		{
			return "home";
		}

	}

	@RequestMapping("/home")
	public String home(Model m) {
		UserModel um = new UserModel();
		m.addAttribute("user", um);
		return "home";
	}

	@RequestMapping("/userLogin")
	public String userlogin(Model m) {
		UserModel um = new UserModel();
		m.addAttribute("user", um);
		return "userLoginPage";
	}

	@RequestMapping("/addCorporate")
	public String addCorporatePage(Model m) {

		m.addAttribute("corporate", new CorporateModel());
		return "addCorporate";
	}

	@PostMapping("/addCorporate")
	public String addCorporate(@ModelAttribute("corporate") CorporateModel cm) {
		int i = coImp.addCorporate(cm);
		if (i > 0)
			return "redirect:/adminPage";
		else
			return "addCorporate";
	}

	@RequestMapping("/adminPage")
	public String CorporatePage(Model m) {
		List<CorporateModel> list = coImp.viewAll();
		m.addAttribute("list", list);
		return "admin";
	}

	@RequestMapping("/updateCorporate")
	public String modifyCorpPage(@RequestParam("corporateId") int Cid, Model m) {
		CorporateModel Cmodel = coImp.getEmpById(Cid);
		m.addAttribute("Corpmodify", Cmodel);
		// System.out.println("id "+Cid);
		return "Cmodify";
	}

	@RequestMapping("/updateCorporatePage")
	public String modifybtn(@ModelAttribute("Corpmodify") CorporateModel cmodel) {
		int res = coImp.modify(cmodel);
		if (res >= 1)
			return "redirect:/adminPage";
		else
			return "Cmodify";
	}

	@RequestMapping("/deleteCorporate")
	public String deletebtn(@RequestParam("corporateId") int Cid, Model m) {
		int i = coImp.delete(Cid);
		if (i > 0)
			return "redirect:/adminPage";
		else
			return "";
	}

}

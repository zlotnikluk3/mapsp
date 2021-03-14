package com.zlotluk.MaPSP.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlotluk.MaPSP.Notifications.FbController;
import com.zlotluk.MaPSP.model.Eventt;
import com.zlotluk.MaPSP.model.Tokenn;
import com.zlotluk.MaPSP.service.EventService;
import com.zlotluk.MaPSP.service.TokenService;

@Controller
public class AppController {

	@Autowired
	private EventService service;

	@Autowired
	private TokenService tservice;

	@Autowired
	private FbController wc;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String goMap(Model model) {
		List<Eventt> events = service.listAll();
		model.addAttribute("events", events);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getName().equals("jrg1bstok")) {
			model.addAttribute("hide", "t");
		} else {
			model.addAttribute("hide", "f");
		}
		return "index";
	}

	@RequestMapping(value = "tokens", method = RequestMethod.GET)
	public String goTok(Model model) {
		List<Tokenn> tokens = tservice.listAll();
		model.addAttribute("tokens", tokens);
		return "tokens";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String back(Model model) {
		List<Eventt> events = service.listAll();
		model.addAttribute("events", events);
		return "index";
	}

	@RequestMapping(value = "coor/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Eventt getCoor(@PathVariable(name = "id") long id) {
		return service.get(id);
	}

	@RequestMapping(value = "last", method = RequestMethod.GET)
	@ResponseBody
	public long getLast() {
		List<Eventt> l = service.listAll();
		Eventt e = (Eventt) l.get(l.size() - 1);
		return e.getId();
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String editEv(@PathVariable(name = "id") long id, @RequestParam("opis") String op,
			@RequestParam("latt") String lat, @RequestParam("lngg") String lng) {
		service.update(id, op, lat, lng);
		wc.send("Aktualizacja zdarzenia", id, op, lat, lng);
		return "redirect:/";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String saveEv(@RequestParam("opis") String op, @RequestParam("latt") String lat,
			@RequestParam("lngg") String lng) {
		service.save(new Eventt(op, lat, lng));
		List<Eventt> le = service.listAll();
		int ls = le.size() - 1;
		wc.send("Nowe zdarzenie", le.get(ls).getId(), op, lat, lng);

		return "redirect:/";
	}

	@RequestMapping(value = "delfromlist/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteFromList(@PathVariable(name = "id") long id) {
		if (id != 0)
			service.delete(id);
		return "redirect:/";
	}

	@RequestMapping(value = "deleteTok/{tok}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String DeleteTok(@PathVariable(name = "tok") String tok) {
		tservice.delete(tok);
		return "redirect:/tokens";
	}

	@RequestMapping(value = "upTok/{tok}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String upTok(@PathVariable(name = "tok") String tok, @RequestParam("saved") String saved,
			@RequestParam("nam") String nam) {
		if (saved.equals("true")) {
			tservice.update(tok, true, nam);
		} else {
			tservice.update(tok, false, nam);
		}
		return "redirect:/tokens";
	}
}
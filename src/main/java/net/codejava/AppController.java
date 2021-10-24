package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@Autowired
	private ProductService service;
	@Autowired
	private ProfileService serviceProfile;



	@RequestMapping("/")
	public String viewHomePage(Model modelProduct) {
		List<Product> listProducts = service.listAll();
		modelProduct.addAttribute("listProducts", listProducts);
		
		return "index";
	}

	@RequestMapping("/profile")
	public String viewHomePageProfile(Model modelProfile) {
		List<Profile> listProfiles = serviceProfile.listAll();
		modelProfile.addAttribute("listProfiles", listProfiles);

		return "indexProfile";
	}

	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "new_product";
	}

	@RequestMapping("/newProfile")
	public String showNewProfilePage(Model modelProfile) {
		Profile profile = new Profile();
		modelProfile.addAttribute("profile", profile);

		return "new_profile";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
		
		return "redirect:/";
	}

	@RequestMapping(value = "/saveProfile", method = RequestMethod.POST)
	public String saveProfile(@ModelAttribute("profile") Profile profile) {
		serviceProfile.save(profile);

		return "redirect:/profile";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = service.get(id);
		mav.addObject("product", product);
		
		return mav;
	}

	@RequestMapping("/editProfile/{id}")
	public ModelAndView showEditProfilePage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_profile");
		Profile profile = serviceProfile.get(id);
		mav.addObject("profile", profile);

		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}

	@RequestMapping("/deleteProfile/{id}")
	public String deleteProfile(@PathVariable(name = "id") int id) {
		serviceProfile.delete(id);
		return "redirect:/profile";
	}
	
}

package org.muj.sbf.controller;

import java.util.List;

import javax.validation.Valid;

import org.muj.sbf.domain.User;
import org.muj.sbf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * UserController is the main controller of this application.
 * This controller servers  add-user , search-user  and default home requests.
 * 
 * @author momujah
 */
@Controller
public class UserController {

	private static final int PAGE_SIZE = 10;
	private static final String ESC_CHAR = "|";

	@Autowired
	private UserService userService;

	/**
	 * Home page and /add-user page are served by this method
	 * 
	 * @param modelMap
	 * @return String The view to be rendered
	 */
	@RequestMapping(value = { "/", "/add-user" }, method = RequestMethod.GET)
	public String user(ModelMap modelMap) {
		modelMap.addAttribute(ViewConstants.ATTRIB_USER, new User());
		return "add-user";
	}

	/**
	 * /add-user Post is served by this method Checks if the first name and last
	 * name are correct and call the service to create a new user.
	 * 
	 * @param user
	 * @param bindingResult
	 * @param modelMap
	 * @return String The view to be rendered
	 */
	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	public String addUser(@ModelAttribute(ViewConstants.ATTRIB_USER) @Valid User user, BindingResult bindingResult,
			ModelMap modelMap) {

		if (!bindingResult.hasErrors()) {
			/*
			 * Avoiding white space in the user input
			 */
			user.setFirstName(user.getFirstName().trim());
			user.setLastName(user.getLastName().trim());
			userService.addUser(user);
			modelMap.addAttribute(ViewConstants.ATTRIB_USER, new User());
			modelMap.addAttribute(ViewConstants.ATTRIB_SAVED, true);
			modelMap.addAttribute(ViewConstants.ATTRIB_MSG_USER_SAVED_SUCCESS, ViewConstants.MSG_USER_SAVED_SUCCESS);
		}
		return "add-user";
	}

	/**
	 * This method uses the request parameter term and page to search users
	 * using the user service. Return the model and view with the list of users
	 * and data for pagination
	 * 
	 * @param term
	 * @param page
	 * @return ModelAndView. Return the model view object with all the objects
	 *         required to render the search page
	 */
	@RequestMapping(value = "/search-user")
	public ModelAndView search(@RequestParam(value = "term", required = false) String term,
			@RequestParam(value = "page", required = false) Integer page) {

		ModelAndView mv = new ModelAndView("search-user");
		int start = 0;

		if (term == null) {
			term = "";
		}
		mv.addObject(ViewConstants.ATTRIB_SEARCH_TERM, term);
		term = prepareSearchTerm(term);
		Long hitsL = userService.searchHitCount(term);

		int hits = 0;
		if (hitsL != null) {
			hits = Math.toIntExact(hitsL);
		}

		mv.addObject(ViewConstants.ATTRIB_SEARCH_HIT_COUNT, hits);

		if (hits != 0) {
			int pageCount = (hits - 1) / PAGE_SIZE + ((hits % PAGE_SIZE == 0) ? 0 : 1);

			if (page == null || page <= 0) {
				page = 1;
			} else if (pageCount > 0 && page > pageCount) {
				page = pageCount;
			}

			start = (page - 1) * PAGE_SIZE;

			List<User> users = userService.search(term, start, PAGE_SIZE);

			if (page == 1) {
				mv.addObject(ViewConstants.ATTRIB_FIRST_PAGE, true);
			} else {
				mv.addObject(ViewConstants.ATTRIB_FIRST_PAGE, false);
				mv.addObject(ViewConstants.ATTRIB_PREV_PAGE_NO, page - 1);
			}
			if (pageCount > page) {
				mv.addObject(ViewConstants.ATTRIB_NEXT_PAGE, true);
				mv.addObject(ViewConstants.ATTRIB_NEXT_PAGE_NO, page + 1);
			} else if (pageCount == page) {
				mv.addObject(ViewConstants.ATTRIB_LAST_PAGE, true);
			}

			mv.addObject(ViewConstants.ATTRIB_USERS, users);

		}

		return mv;
	}

	/**
	 * Make the term lower. Escape for special character for like search
	 * 
	 * @param term
	 * @return String. Modified term
	 */
	private String prepareSearchTerm(String term) {
		return term.trim().toLowerCase().replace(ESC_CHAR, ESC_CHAR + ESC_CHAR).replace("%", ESC_CHAR + "%").replace("_",
				ESC_CHAR + "_");
	}

}

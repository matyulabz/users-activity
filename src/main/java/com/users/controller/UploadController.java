package com.users.controller;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.users.config.PictureUploadProperties;
import com.users.entities.User;
import com.users.services.UserService;

@Controller
public class UploadController {
	private final Resource anonymousPicture;

	@Autowired
	private UserService userService;

	@Autowired
	public UploadController(PictureUploadProperties uploadProperties) {
		anonymousPicture = uploadProperties.getAnonymousPicture();
	}

	@ModelAttribute("picturePath")
	public Resource picturePath() {
		return anonymousPicture;
	}

	@RequestMapping("/user/upload/{id}")
	public String uploadPage(@PathVariable long id, Model model, Authentication auth) {
		User user = userService.findById(id);
		if (auth.getName().equals(user.getUsername())) {
			model.addAttribute("userId", id);
			return "/user/photoUpload";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "/user/upload/{id}", method = RequestMethod.POST)
	public String onUpload(MultipartFile file, @PathVariable long id, RedirectAttributes redirectAttrs, Model model)
			throws IOException {
		if (file.isEmpty() || !isImage(file)) {
			redirectAttrs.addFlashAttribute("error", "Incorrect file. Please upload a picture.");
			return "redirect:/upload";
		}
		userService.updatePicture(file.getBytes(), id);
		return "redirect:/user/profile/" + id;
	}

	@RequestMapping(value = "/uploadedPicture/{id}")
	public void getUploadedPicture(HttpServletResponse response, @PathVariable long id,
			@ModelAttribute("picturePath") Resource picturePath) throws IOException {
		User user = userService.findById(id);
		if (user.getProfilePicture() == null) {
			response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(picturePath.toString()));
			Path path = Paths.get(picturePath.getURI());
			Files.copy(path, response.getOutputStream());
		} else {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(user.getProfilePicture());
			response.getOutputStream().close();
		}
	}

	private boolean isImage(MultipartFile file) {
		return file.getContentType().startsWith("image");
	}

}

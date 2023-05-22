package com.eventManagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eventManagement.dto.GiftDto;
import com.eventManagement.model.Gift;
import com.eventManagement.service.GiftService;

@RestController
@RequestMapping("/gift")
public class GiftController {

	@Autowired
	GiftService giftService;

	@PostMapping("/addGift")
	public ResponseEntity<String> addGift(@RequestPart("file") MultipartFile file,
			@RequestPart("data") @Valid GiftDto giftDto, BindingResult result) {

		if (result.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append(". "));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
		}
		String response = "";
		try {
			response = giftService.addGift(giftDto, file);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/updateGift/{giftId}")
	public ResponseEntity<String> updateGift(@RequestPart("file") MultipartFile file,
			@RequestPart("data") @Valid GiftDto giftDto, BindingResult result, @PathVariable("giftId") Long giftId) {

		if (result.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append(". "));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
		}
		String response = "";
		try {
			response = giftService.updateGift(giftId, giftDto, file);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/findGift/{giftId}")
	public ResponseEntity<?> findGift(@PathVariable("giftId") Long giftId) {

		Gift gift = null;
		try {
			gift = giftService.findGift(giftId);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		if(gift == null) {
			return ResponseEntity.ok("no such gift exist");
		}
		return ResponseEntity.ok(gift);
	}

	@GetMapping("/getAllGift/{adminId}")
	public ResponseEntity<?> getAllGift(@PathVariable("adminId") Long adminId) {

		List<Gift> giftList = null;
		try {
			giftList = giftService.findAllGift(adminId);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		if(giftList == null) {
			return ResponseEntity.ok("no such gift exist");
		}
		return ResponseEntity.ok(giftList);
	}
}

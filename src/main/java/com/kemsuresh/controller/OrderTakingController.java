package com.kemsuresh.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kemsuresh.data.Order;

@RestController
public class OrderTakingController {
	
	
	@Autowired
	private RabbitTemplate template;
	
	private static final String ORDER_FULFILMENT_SYSTEM = "order.fulfilment.system";
	private static final String CARE_SYSTEM = "care.system";
	
	@PostMapping(
	        value = "/order",
	        consumes = {MediaType.APPLICATION_JSON_VALUE},
	        produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<String> order(@Valid @RequestBody Order order) {
		ResponseEntity<String> response = ResponseEntity.ok("Your Order has been placed, our team will get back to you soon. Your Order ID for reference is "+ order.getId());
		template.convertAndSend(ORDER_FULFILMENT_SYSTEM, order.toString());
		template.convertAndSend(ORDER_FULFILMENT_SYSTEM, order.toString());
		return response;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public @ResponseBody Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}

}

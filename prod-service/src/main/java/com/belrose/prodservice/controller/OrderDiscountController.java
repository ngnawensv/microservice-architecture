package com.belrose.prodservice.controller;

import com.belrose.prodservice.model.drools.OrderDiscount;
import com.belrose.prodservice.model.drools.OrderRequest;
import com.belrose.prodservice.service.impl.OrderDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class OrderDiscountController {

	@Autowired
	private OrderDiscountService orderDiscountService;

	@PostMapping("/get-discount")
	public ResponseEntity<OrderDiscount> getDiscount(@RequestBody OrderRequest orderRequest) {
		OrderDiscount discount = orderDiscountService.getDiscount(orderRequest);
		return new ResponseEntity<>(discount, HttpStatus.OK);
	}
}
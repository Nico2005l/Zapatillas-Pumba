package com.uade.tpo.zapatillasPumba.controllers.orders;

import lombok.Data;


@Data
public class OrderRequest {
	private Long userId;
	private String status;
}

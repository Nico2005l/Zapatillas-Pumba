package com.uade.tpo.zapatillasPumba.controllers.orders;

import lombok.Data;
import java.util.List;

import com.uade.tpo.zapatillasPumba.controllers.orderItems.OrderItemRequest;

@Data
public class OrderRequest {
	private Long userId;
	private String status;
}

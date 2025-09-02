package com.uade.tpo.zapatillasPumba.controllers.orders;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
	private Long userId;
	private Double total;
	private String status;
	private List<Long> itemIds;
}

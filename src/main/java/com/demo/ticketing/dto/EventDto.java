package com.demo.ticketing.dto;

import java.math.BigDecimal;

public record EventDto(String eventName, BigDecimal price) {
}

//public record TicketDto(String id, String eventId, BigDecimal price, )


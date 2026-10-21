package com.demo.ticketing.repo;

import com.demo.ticketing.dao.Event;
import com.demo.ticketing.dto.EventDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class EventRepository {
    private final JdbcTemplate jdbc;

    public EventRepository(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    public void storeEvent(EventDto eventDto) {
        String sql = "INSERT INTO EVENTS (`id`, `event_name`, `price`) VALUES (?, ?, ?)";

        jdbc.update(sql,
                UUID.randomUUID(),
                eventDto.eventName(),
                eventDto.price()
                );
    }

    public List<Event> findAllEvents() {
        String sql = "SELECT * FROM EVENTS";

        RowMapper<Event> eventRowMapper = (r, i) -> {
            Event rowObject = new Event();
            rowObject.setEventId(r.getString("id"));
            rowObject.setEventName(r.getString("event_name"));
            rowObject.setPrice(r.getBigDecimal("price"));

            return rowObject;
        };

        return jdbc.query(sql, eventRowMapper);

    }
}

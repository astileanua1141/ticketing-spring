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
        String sql = "INSERT INTO EVENTS (`id`, `event_name`, `price`, `capacity`) VALUES (?, ?, ?, ?)";
        String sqlMeta = "INSERT INTO EVENT_META (`id`, `event_id`, `row_key`, `row_value`) VALUES (?, ?, ?, ?)";

        String newEventId = UUID.randomUUID().toString();

        jdbc.update(sql,
                newEventId,
                eventDto.eventName(),
                eventDto.price(),
                eventDto.capacity()
                );

        jdbc.update(sqlMeta,
                UUID.randomUUID(),
                newEventId,
                "AVAILABLE_CAPACITY",
                eventDto.capacity()
                );
    }

    public List<Event> findAllEvents() {
        String sql = "SELECT e.id, e.event_name, e.price, e.capacity, em.row_value as `available_capacity_value` " +
                " FROM EVENTS e JOIN EVENT_META em on e.id = em.event_id" ;
//                " WHERE em.row_key = `AVAILABLE_CAPACITY`";
        Event rowObject = new Event();


        RowMapper<Event> eventRowMapper = (r, i) -> {
            rowObject.setEventId(r.getString("id"));
            rowObject.setEventName(r.getString("event_name"));
            rowObject.setPrice(r.getBigDecimal("price"));

            Integer capacity = r.getInt("capacity");
            rowObject.setCapacity(capacity);

            Integer availableCapacityValue = Integer.parseInt(r.getString("available_capacity_value"));
            rowObject.setAvailableCapacity(availableCapacityValue);

            rowObject.setSoldOut(availableCapacityValue == 0);

            return rowObject;
        };

        return jdbc.query(sql, eventRowMapper);

    }
}

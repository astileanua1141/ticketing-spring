package com.demo.ticketing.controller;

import com.demo.ticketing.dao.Event;
import com.demo.ticketing.dto.EventDto;
import com.demo.ticketing.repo.EventRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticketing")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @PostMapping("/event")
    public void createEvent(@RequestBody EventDto eventInput){
//        System.out.println(eventInput.toString());
        eventRepository.storeEvent(eventInput);
    }

    @GetMapping("/event")
    public List<Event> findAllEvents(){
        return  eventRepository.findAllEvents();
    }
}

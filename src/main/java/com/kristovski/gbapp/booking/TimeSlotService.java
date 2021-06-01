package com.kristovski.gbapp.booking;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSlotService {

    private TimeSlotRepository timeSlotRepository;

    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public List<TimeSlot> findAll(){
        return timeSlotRepository.findAll();
    }
}

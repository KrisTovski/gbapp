package com.kristovski.gbapp.room;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {

    private RoomRepository roomRepository;

    public List<Room> findAll(){
        return roomRepository.findAll();
    }
}

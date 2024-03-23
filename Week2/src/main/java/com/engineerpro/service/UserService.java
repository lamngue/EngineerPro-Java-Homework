package com.engineerpro.service;

import com.engineerpro.model.Booking;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Booking optimisticBookRoom(int userId, int roomId);
    Booking pessimisticBookRoom(int userId, int roomId);
}


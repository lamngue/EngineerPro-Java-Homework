package com.engineerpro.repository;

import com.engineerpro.model.Room;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    Room findOneByIdAndAvailable(int id, boolean available);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Room findByIdAndAvailable(int id, boolean available);

    @Modifying
    @Query("update Room set available = false, version = version + 1 where id = :id and version = :version")
    int updateRoomAsUnavailable(@Param(value = "id") int id, @Param(value = "version") int version);

    @Modifying
    @Query("update Room set available = false where id = :id and available = true")
    int updateRoomAsUnavailableWhenPessimisticLocked(@Param(value = "id") int id);
}

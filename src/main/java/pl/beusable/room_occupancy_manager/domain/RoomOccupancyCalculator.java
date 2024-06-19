package pl.beusable.room_occupancy_manager.domain;

import pl.beusable.room_occupancy_manager.domain.model.RoomOccupancy;
import pl.beusable.room_occupancy_manager.domain.model.RoomList;

public interface RoomOccupancyCalculator {

    RoomOccupancy calculate(RoomList roomList);
}

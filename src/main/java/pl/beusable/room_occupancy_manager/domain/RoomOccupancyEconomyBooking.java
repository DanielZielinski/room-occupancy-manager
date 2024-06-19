package pl.beusable.room_occupancy_manager.domain;

import pl.beusable.room_occupancy_manager.domain.model.RoomList;

interface RoomOccupancyEconomyBooking {
    void bookEconomyRooms(RoomList roomList);
}

package pl.beusable.room_occupancy_manager.domain;

import pl.beusable.room_occupancy_manager.domain.model.RoomList;

interface RoomOccupancyPremiumBooking {

    void bookPremiumRooms(RoomList roomList);
}

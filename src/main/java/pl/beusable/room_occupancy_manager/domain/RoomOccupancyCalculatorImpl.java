package pl.beusable.room_occupancy_manager.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.beusable.room_occupancy_manager.domain.model.RoomOccupancy;
import pl.beusable.room_occupancy_manager.domain.model.RoomList;


@Service
@RequiredArgsConstructor
class RoomOccupancyCalculatorImpl implements RoomOccupancyCalculator {


    private final RoomOccupancyEconomyBooking roomOccupancyEconomyBooking;
    private final RoomOccupancyPremiumBooking roomOccupancyPremiumBooking;

    @Override
    public RoomOccupancy calculate(RoomList roomList) {
        roomOccupancyPremiumBooking.bookPremiumRooms(roomList);
        roomOccupancyEconomyBooking.bookEconomyRooms(roomList);
        return RoomOccupancy.from(roomList);
    }


}


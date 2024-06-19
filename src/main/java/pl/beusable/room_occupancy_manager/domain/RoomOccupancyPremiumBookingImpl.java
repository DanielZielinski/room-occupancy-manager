package pl.beusable.room_occupancy_manager.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.beusable.room_occupancy_manager.domain.model.RoomList;
import pl.beusable.room_occupancy_manager.domain.model.RoomType;

@Service
@RequiredArgsConstructor
class RoomOccupancyPremiumBookingImpl implements RoomOccupancyPremiumBooking {

    private final RoomOccupancyPossiblePaymentsProvider roomOccupancyPossiblePaymentsProvider;

    @Override
    public void bookPremiumRooms(RoomList roomList) {
        roomOccupancyPossiblePaymentsProvider.getPremiumPossiblePayments()
                .forEach(possiblePayment -> roomList.bookRoom(RoomType.PREMIUM, possiblePayment));
    }
}

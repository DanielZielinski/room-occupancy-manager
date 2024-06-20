package pl.beusable.room_occupancy_manager.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.beusable.room_occupancy_manager.domain.model.PossiblePayment;
import pl.beusable.room_occupancy_manager.domain.model.RoomList;
import pl.beusable.room_occupancy_manager.domain.model.RoomType;

import java.util.List;

@Service
@RequiredArgsConstructor
class RoomOccupancyEconomyBookingImpl implements RoomOccupancyEconomyBooking {

    private final RoomOccupancyPossiblePaymentsProvider roomOccupancyPossiblePaymentsProvider;

    @Override
    public void bookEconomyRooms(RoomList roomList) {

        List<PossiblePayment> economyPossiblePayments = roomOccupancyPossiblePaymentsProvider.getEconomyPossiblePayments();

        if (notEnoughEconomyRooms(roomList, economyPossiblePayments)) {
            bookEconomyInPremium(roomList, economyPossiblePayments);
            bookRestOfEconomyInEconomy(roomList, economyPossiblePayments);
        } else {
            bookAlInEconomy(roomList, economyPossiblePayments);
        }
    }

    private void bookEconomyInPremium(RoomList roomList, List<PossiblePayment> economyPossiblePayments){
        int possibleUpgrades = calculatePossibleUpgrades(roomList, economyPossiblePayments);
        for (int i = 0; i < possibleUpgrades; i++) {
            roomList.bookRoom(RoomType.PREMIUM, economyPossiblePayments.get(i));
        }
    }

    private void bookRestOfEconomyInEconomy(RoomList roomList, List<PossiblePayment> economyPossiblePayments){
        economyPossiblePayments
                .stream()
                .filter(possiblePayment -> !possiblePayment.isUsed())
                .forEach(possiblePayment -> roomList.bookRoom(RoomType.ECONOMY, possiblePayment));
    }

    private void bookAlInEconomy(RoomList roomList, List<PossiblePayment> economyPossiblePayments){
        economyPossiblePayments.forEach(possiblePayment -> roomList.bookRoom(RoomType.ECONOMY, possiblePayment));
    }

    private int calculatePossibleUpgrades(RoomList roomList, List<PossiblePayment> economyPossiblePayments) {
        int missingEconomyRooms = economyPossiblePayments.size() - roomList.numberOfRooms(RoomType.ECONOMY);
        return Math.min(missingEconomyRooms, roomList.numberOfNotOccupiedRooms(RoomType.PREMIUM));
    }

    private boolean notEnoughEconomyRooms(RoomList roomList, List<PossiblePayment> economyPossiblePayments) {
        return roomList.numberOfRooms(RoomType.ECONOMY) < economyPossiblePayments.size();
    }
}

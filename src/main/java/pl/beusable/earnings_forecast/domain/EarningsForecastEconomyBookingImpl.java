package pl.beusable.earnings_forecast.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.beusable.earnings_forecast.domain.model.PossiblePayment;
import pl.beusable.earnings_forecast.domain.model.RoomList;
import pl.beusable.earnings_forecast.domain.model.RoomType;

import java.util.List;

@Service
@RequiredArgsConstructor
class EarningsForecastEconomyBookingImpl implements EarningsForecastEconomyBooking {

    private final PossiblePaymentsProvider possiblePaymentsProvider;

    @Override
    public void bookEconomyRooms(RoomList roomList) {

        List<PossiblePayment> economyPossiblePayments = possiblePaymentsProvider.getEconomyPossiblePayments();

        if (roomList.numberOfRooms(RoomType.ECONOMY) < economyPossiblePayments.size()) {
            int possibleEconomyToPremiumUpgrades = calculatePossibleEconomyToPremiumUpgrades(roomList, economyPossiblePayments);

            for (int i = 0; i < possibleEconomyToPremiumUpgrades; i++) {
                PossiblePayment possiblePayment = economyPossiblePayments.get(i);
                roomList.bookRoom(RoomType.PREMIUM, possiblePayment);
            }
            economyPossiblePayments.stream().filter(possiblePayment -> !possiblePayment.isUsed())
                    .forEach(possiblePayment -> roomList.bookRoom(RoomType.ECONOMY, possiblePayment));
        } else {
            economyPossiblePayments.forEach(possiblePayment -> roomList.bookRoom(RoomType.ECONOMY, possiblePayment));
        }
    }

    private int calculatePossibleEconomyToPremiumUpgrades(RoomList roomList, List<PossiblePayment> economyPossiblePayments) {
        int missingEconomyRooms = economyPossiblePayments.size() - roomList.numberOfRooms(RoomType.ECONOMY);
        return Math.min(missingEconomyRooms, roomList.numberOfNotOccupiedRooms(RoomType.PREMIUM));
    }
}

package pl.beusable.earnings_forecast.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.beusable.earnings_forecast.domain.model.RoomList;
import pl.beusable.earnings_forecast.domain.model.RoomType;

@Service
@RequiredArgsConstructor
class EarningsForecastPremiumBookingImpl implements EarningsForecastPremiumBooking {

    private final PossiblePaymentsProvider possiblePaymentsProvider;

    @Override
    public void bookPremiumRooms(RoomList roomList) {
        possiblePaymentsProvider.getPremiumPossiblePayments()
                .forEach(possiblePayment -> roomList.bookRoom(RoomType.PREMIUM, possiblePayment));
    }
}

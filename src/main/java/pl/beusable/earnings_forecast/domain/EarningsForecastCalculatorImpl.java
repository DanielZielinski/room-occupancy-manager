package pl.beusable.earnings_forecast.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.beusable.earnings_forecast.domain.model.EarningForecast;
import pl.beusable.earnings_forecast.domain.model.RoomList;


@Service
@RequiredArgsConstructor
class EarningsForecastCalculatorImpl implements EarningsForecastCalculator {


    private final EarningsForecastEconomyBooking earningsForecastEconomyBooking;
    private final EarningsForecastPremiumBooking earningsForecastPremiumBooking;

    @Override
    public EarningForecast calculate(RoomList roomList) {
        earningsForecastPremiumBooking.bookPremiumRooms(roomList);
        earningsForecastEconomyBooking.bookEconomyRooms(roomList);
        return EarningForecast.from(roomList);
    }


}


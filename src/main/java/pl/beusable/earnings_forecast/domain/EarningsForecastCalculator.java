package pl.beusable.earnings_forecast.domain;

import pl.beusable.earnings_forecast.domain.model.EarningForecast;
import pl.beusable.earnings_forecast.domain.model.RoomList;

public interface EarningsForecastCalculator {

    EarningForecast calculate(RoomList roomList);
}

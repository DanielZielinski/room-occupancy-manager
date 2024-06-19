package pl.beusable.earnings_forecast.endpoint.model;


import pl.beusable.earnings_forecast.domain.model.EarningForecast;

import java.math.BigDecimal;

public record CalculateEarningsForecastResponse(BigDecimal premiumRoomPrice, int premiumRoomCount,
                                                BigDecimal economyRoomPrice, int economyRoomCount) {

    public static CalculateEarningsForecastResponse from(EarningForecast earningForecast) {
        return new CalculateEarningsForecastResponse(earningForecast.premiumRoomPrice(),
                earningForecast.premiumRoomCount(),
                earningForecast.economyRoomPrice(),
                earningForecast.economyRoomCount());
    }
}

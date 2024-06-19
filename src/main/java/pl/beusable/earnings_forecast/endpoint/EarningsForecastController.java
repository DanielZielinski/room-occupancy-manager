package pl.beusable.earnings_forecast.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.beusable.earnings_forecast.domain.EarningsForecastCalculator;
import pl.beusable.earnings_forecast.domain.model.RoomList;
import pl.beusable.earnings_forecast.endpoint.model.CalculateEarningsForecastRequest;
import pl.beusable.earnings_forecast.endpoint.model.CalculateEarningsForecastResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/earnings-forecast")
class EarningsForecastController {

    private final EarningsForecastCalculator earningsForecastCalculator;

    @PostMapping
    public ResponseEntity<CalculateEarningsForecastResponse> calculateEarningsForecast(@RequestBody CalculateEarningsForecastRequest request) {
        return ResponseEntity.ok(
                CalculateEarningsForecastResponse.from(earningsForecastCalculator
                                .calculate(new RoomList(request.numberOfEconomyRooms(), request.numberOfPremiumRooms()))));


    }
}

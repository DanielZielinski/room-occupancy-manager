package pl.beusable.room_occupancy_manager.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.beusable.room_occupancy_manager.domain.RoomOccupancyCalculator;
import pl.beusable.room_occupancy_manager.domain.model.RoomList;
import pl.beusable.room_occupancy_manager.endpoint.model.CalculateRoomOccupancyRequest;
import pl.beusable.room_occupancy_manager.endpoint.model.CalculateRoomOccupancyResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/room-occupancy")
class RoomOccupancyController {

    private final RoomOccupancyCalculator roomOccupancyCalculator;

    @PostMapping
    public ResponseEntity<CalculateRoomOccupancyResponse> calculateEarningsForecast(@RequestBody CalculateRoomOccupancyRequest request) {
        return ResponseEntity.ok(
                CalculateRoomOccupancyResponse.from(roomOccupancyCalculator
                                .calculate(new RoomList(request.numberOfEconomyRooms(), request.numberOfPremiumRooms()))));


    }
}

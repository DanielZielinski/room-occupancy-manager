package pl.beusable.room_occupancy_manager.endpoint.model;


import pl.beusable.room_occupancy_manager.domain.model.RoomOccupancy;

import java.math.BigDecimal;

public record CalculateRoomOccupancyResponse(BigDecimal premiumRoomPrice, int premiumRoomCount,
                                             BigDecimal economyRoomPrice, int economyRoomCount) {

    public static CalculateRoomOccupancyResponse from(RoomOccupancy roomOccupancy) {
        return new CalculateRoomOccupancyResponse(roomOccupancy.premiumRoomPrice(),
                roomOccupancy.premiumRoomCount(),
                roomOccupancy.economyRoomPrice(),
                roomOccupancy.economyRoomCount());
    }
}

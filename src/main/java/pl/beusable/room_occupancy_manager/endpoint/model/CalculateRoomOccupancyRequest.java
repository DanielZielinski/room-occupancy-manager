package pl.beusable.room_occupancy_manager.endpoint.model;


public record CalculateRoomOccupancyRequest(int numberOfPremiumRooms, int numberOfEconomyRooms) {
}

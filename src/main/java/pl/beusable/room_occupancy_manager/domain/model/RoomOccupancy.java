package pl.beusable.room_occupancy_manager.domain.model;

import java.math.BigDecimal;
import java.util.List;

public record RoomOccupancy(BigDecimal premiumRoomPrice, int premiumRoomCount, BigDecimal economyRoomPrice,
                            int economyRoomCount) {

    public static RoomOccupancy from(RoomList roomList) {
        List<Room> occupiedRooms = roomList.getRooms().stream()
                .filter(Room::isOccupied)
                .toList();
        List<Room> occupiedPremiumRooms = occupiedRooms.stream()
                .filter(room -> room.getType() == RoomType.PREMIUM)
                .toList();

        List<Room> occupiedEconomyRooms = occupiedRooms.stream()
                .filter(room -> room.getType() == RoomType.ECONOMY)
                .toList();

        BigDecimal premiumRoomPrice = occupiedPremiumRooms.stream().map(Room::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal economyRoomPrice = occupiedEconomyRooms.stream().map(Room::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new RoomOccupancy(premiumRoomPrice, occupiedPremiumRooms.size(), economyRoomPrice, occupiedEconomyRooms.size());
    }
}

package pl.beusable.room_occupancy_manager.domain.model;

import lombok.Data;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Data
public class RoomList {

    private List<Room> rooms;

    public RoomList(int numberOfEconomyRooms, int numberOfPremiumRooms) {
        this.rooms = Stream.concat(
                        IntStream.rangeClosed(1, numberOfEconomyRooms)
                                .mapToObj(value -> new Room(RoomType.ECONOMY)),
                        IntStream.rangeClosed(1, numberOfPremiumRooms)
                                .mapToObj(value -> new Room(RoomType.PREMIUM)))
                .toList();
    }


    public boolean bookRoom(RoomType type, PossiblePayment possiblePayment) {
        return rooms.stream().filter(r -> r.getType() == type && r.isNotOccupied())
                .findFirst()
                .map(room -> {
                    possiblePayment.setUsed(true);
                    return room.book(possiblePayment.getPrice());
                })
                .orElse(false);
    }

    public int numberOfRooms(RoomType type) {
        return rooms.stream()
                .filter(room -> room.getType() == type)
                .toList()
                .size();
    }

    public int numberOfNotOccupiedRooms(RoomType type) {
        return rooms.stream()
                .filter(room -> room.getType() == type && room.isNotOccupied())
                .toList()
                .size();
    }

    public int numberOfOccupiedRooms(RoomType type) {
        return rooms.stream()
                .filter(room -> room.getType() == type && room.isOccupied())
                .toList()
                .size();
    }
}

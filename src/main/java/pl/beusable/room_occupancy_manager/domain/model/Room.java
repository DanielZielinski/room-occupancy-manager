package pl.beusable.room_occupancy_manager.domain.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class Room {

    private final RoomType type;
    private boolean occupied;
    private BigDecimal price;

    public boolean book(BigDecimal price) {
        if (occupied) {
            return false;
        } else {
            this.occupied = true;
            this.price = price;
            return true;
        }
    }

    public boolean isNotOccupied(){
        return !occupied;
    }

}

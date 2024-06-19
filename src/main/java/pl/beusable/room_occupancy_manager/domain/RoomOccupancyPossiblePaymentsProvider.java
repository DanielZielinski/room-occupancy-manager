package pl.beusable.room_occupancy_manager.domain;

import pl.beusable.room_occupancy_manager.domain.model.PossiblePayment;

import java.util.List;

interface RoomOccupancyPossiblePaymentsProvider {


    List<PossiblePayment> getPremiumPossiblePayments();

    List<PossiblePayment> getEconomyPossiblePayments();
}

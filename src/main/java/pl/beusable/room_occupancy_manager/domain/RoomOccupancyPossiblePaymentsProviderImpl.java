package pl.beusable.room_occupancy_manager.domain;

import org.springframework.stereotype.Service;
import pl.beusable.room_occupancy_manager.domain.model.PossiblePayment;

import java.util.List;
import java.util.stream.Stream;

@Service
class RoomOccupancyPossiblePaymentsProviderImpl implements RoomOccupancyPossiblePaymentsProvider {

    @Override
    public List<PossiblePayment> getPremiumPossiblePayments() {
        return getPossiblePayments()
                .stream().filter(PossiblePayment::isPremiumClient).toList();
    }

    @Override
    public List<PossiblePayment> getEconomyPossiblePayments() {
        return getPossiblePayments()
                .stream().filter(PossiblePayment::isEconomyClient).toList();
    }

    private List<PossiblePayment> getPossiblePayments() {
        return Stream.of(
                        PossiblePayment.from(23),
                        PossiblePayment.from(45),
                        PossiblePayment.from(155),
                        PossiblePayment.from(374),
                        PossiblePayment.from(22),
                        PossiblePayment.from(99.99),
                        PossiblePayment.from(100),
                        PossiblePayment.from(101),
                        PossiblePayment.from(115),
                        PossiblePayment.from(209))
                .sorted()
                .toList();

    }
}

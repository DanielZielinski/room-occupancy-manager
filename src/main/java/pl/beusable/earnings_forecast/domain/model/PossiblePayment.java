package pl.beusable.earnings_forecast.domain.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class PossiblePayment implements Comparable<PossiblePayment> {

    private final BigDecimal price;
    private boolean used;

    public static PossiblePayment from(double possiblePayment){
        return new PossiblePayment(BigDecimal.valueOf(possiblePayment));
    }

    @Override
    public int compareTo(PossiblePayment o) {
        return o.getPrice().compareTo(this.price);
    }

    public boolean isPremiumClient(){
        return this.getPrice().compareTo(BigDecimal.valueOf(100)) >= 0;
    }

    public boolean isEconomyClient(){
        return !this.isPremiumClient();
    }
}

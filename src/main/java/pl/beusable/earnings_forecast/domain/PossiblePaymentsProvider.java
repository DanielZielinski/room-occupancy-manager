package pl.beusable.earnings_forecast.domain;

import pl.beusable.earnings_forecast.domain.model.PossiblePayment;

import java.util.List;

interface PossiblePaymentsProvider {


    List<PossiblePayment> getPremiumPossiblePayments();

    List<PossiblePayment> getEconomyPossiblePayments();
}

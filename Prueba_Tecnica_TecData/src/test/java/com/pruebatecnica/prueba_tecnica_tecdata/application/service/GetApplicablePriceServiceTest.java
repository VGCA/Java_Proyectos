package com.pruebatecnica.prueba_tecnica_tecdata.application.service;

import com.pruebatecnica.prueba_tecnica_tecdata.application.port.out.PriceRepositoryPort;
import com.pruebatecnica.prueba_tecnica_tecdata.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetApplicablePriceServiceTest {

    private static final LocalDateTime DATE = LocalDateTime.of(2020, 6, 14, 16, 0);

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private GetApplicablePriceService service;

    private Price price(int priceList, int priority, String amount) {
        return new Price(1, LocalDateTime.of(2020, 6, 14, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59),
                priceList, 35455, priority, new BigDecimal(amount), "EUR");
    }

    @Test
    void returnsThePriceWithHighestPriorityWhenSeveralOverlap() {
        Price low = price(1, 0, "35.50");
        Price high = price(2, 1, "25.45");
        when(priceRepositoryPort.findApplicablePrices(35455, 1, DATE)).thenReturn(List.of(low, high));

        Optional<Price> result = service.getApplicablePrice(35455, 1, DATE);

        assertTrue(result.isPresent());
        assertEquals(high, result.get());
        verify(priceRepositoryPort).findApplicablePrices(35455, 1, DATE);
    }

    @Test
    void returnsTheOnlyPriceWhenThereIsJustOne() {
        Price only = price(1, 0, "35.50");
        when(priceRepositoryPort.findApplicablePrices(35455, 1, DATE)).thenReturn(List.of(only));

        assertEquals(Optional.of(only), service.getApplicablePrice(35455, 1, DATE));
    }

    @Test
    void returnsEmptyWhenNoPriceApplies() {
        when(priceRepositoryPort.findApplicablePrices(35455, 1, DATE)).thenReturn(List.of());

        assertTrue(service.getApplicablePrice(35455, 1, DATE).isEmpty());
    }
}

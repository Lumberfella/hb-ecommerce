package no.emiljensen.hbecommerce.checkout;

import no.emiljensen.hbecommerce.watch.Watch;
import no.emiljensen.hbecommerce.watch.WatchRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CheckoutServiceTest {

    @Test
    public void testGetCheckoutPrice_NoWatches_ReturnsZero() {
        WatchRepository watchRepository = Mockito.mock(WatchRepository.class);
        CheckoutService CheckoutService = new CheckoutService(watchRepository);

        List<String> ids = Collections.emptyList();
        Integer result = CheckoutService.getCheckoutPrice(ids);

        assertEquals(0, result);
    }

    @Test
    public void testGetCheckoutPrice_WatchesWithSomeDiscount_ReturnsCorrectTotalAmount() {
        WatchRepository watchRepository = Mockito.mock(WatchRepository.class);
        CheckoutService CheckoutService = new CheckoutService(watchRepository);

        Watch watch1 = new Watch("001", "Rolex", 100, "3 for 200");
        Watch watch2 = new Watch("002", "Michael Kors", 80, "2 for 120");
        Watch watch3 = new Watch("003", "Swatch", 50, null);
        Watch watch4 = new Watch("004", "Casio", 30, null);

        List<String> watchIds = List.of("001", "002", "001", "004", "003");

        when(watchRepository.findAllById(watchIds))
                .thenReturn(List.of(watch1, watch2, watch4, watch3));

        Integer result = CheckoutService.getCheckoutPrice(watchIds);

        assertEquals(360, result);
    }

    @Test
    public void testGetCheckoutPrice_MultipleDiscountSameWatchType_ReturnsCorrectTotalAmount() {
        WatchRepository watchRepository = Mockito.mock(WatchRepository.class);
        CheckoutService CheckoutService = new CheckoutService(watchRepository);

        Watch watch2 = new Watch("002", "Michael Kors", 80, "2 for 120");

        List<String> watchIds = List.of("002", "002", "002", "002", "002");

        when(watchRepository.findAllById(watchIds))
                .thenReturn(List.of(watch2));

        Integer result = CheckoutService.getCheckoutPrice(watchIds);

        assertEquals(320, result);
    }

    @Test
    public void testGetCheckoutPrice_PrimeNumberDiscount_ReturnsCorrectTotalAmount() {
        WatchRepository watchRepository = Mockito.mock(WatchRepository.class);
        CheckoutService CheckoutService = new CheckoutService(watchRepository);

        Watch watch2 = new Watch("002", "Michael Kors", 80, "3 for 120");

        List<String> watchIds = List.of("002", "002", "002", "002", "002");

        when(watchRepository.findAllById(watchIds))
                .thenReturn(List.of(watch2));

        Integer result = CheckoutService.getCheckoutPrice(watchIds);

        assertEquals(280, result);
    }

    @Test
    void testGetCheckoutPrice_EmptyWatchList_ReturnsZero() {
        WatchRepository watchRepository = Mockito.mock(WatchRepository.class);
        CheckoutService CheckoutService = new CheckoutService(watchRepository);

        Integer checkoutPrice = CheckoutService.getCheckoutPrice(Collections.emptyList());

        assertEquals(0, checkoutPrice);
    }
}

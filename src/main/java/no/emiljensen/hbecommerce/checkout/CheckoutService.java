package no.emiljensen.hbecommerce.checkout;

import no.emiljensen.hbecommerce.watch.Watch;
import no.emiljensen.hbecommerce.watch.WatchRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckoutService {

    private final WatchRepository watchRepository;

    public CheckoutService(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }

    public int getCheckoutPrice(List<String> ids) {
        List<Watch> watches = watchRepository.findAllById(ids);

        Map<String, Integer> distinctWatchCount = getDistinctWatchIdCount(ids);

        int checkoutPrice = 0;

        for (Watch watch : watches) {
            int count = distinctWatchCount.get(watch.getWatchId());
            int totalPriceForWatchType = calculateTotalPriceOfWatchType(watch, count);
            checkoutPrice += totalPriceForWatchType;
        }

        return checkoutPrice;
    }

    private Map<String, Integer> getDistinctWatchIdCount(List<String> watchIds) {
        Map<String, Integer> distinctWatchIdCount = new HashMap<>();

        for (String id : watchIds) {
            distinctWatchIdCount.put(id, distinctWatchIdCount.getOrDefault(id, 0) + 1);
        }

        return distinctWatchIdCount;
    }

    private int calculateTotalPriceOfWatchType(Watch watch, int count) {
        if (watch.getDiscount() != null) {
            DiscountInfo discountInfo = new DiscountInfo(watch.getDiscount());

            // Discarding fractional part, truncated towards zero
            int bundledDiscountedWatchesCount = count / discountInfo.getDiscountQuantity();
            int remainingWatchesCount = count % discountInfo.getDiscountQuantity();

            int bundledDiscountTotalPrice = bundledDiscountedWatchesCount * discountInfo.getDiscountPrice();
            int remainingWatchesTotalPrice = remainingWatchesCount * watch.getUnitPrice();

            return bundledDiscountTotalPrice + remainingWatchesTotalPrice;
        } else {
            return count * watch.getUnitPrice();
        }
    }
}

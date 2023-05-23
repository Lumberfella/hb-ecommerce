package no.emiljensen.hbecommerce.checkout;

public class DiscountInfo {

    private final int quantity;
    private final int price;

    public DiscountInfo(String discount) {
        String[] parts = discount.split(" for ");
        this.quantity = Integer.parseInt(parts[0]);
        this.price = Integer.parseInt(parts[1]);
    }

    public int getDiscountQuantity() {
        return quantity;
    }

    public int getDiscountPrice() {
        return price;
    }
}

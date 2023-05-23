package no.emiljensen.hbecommerce.watch;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Watch {

    @Id
    private String watchId;
    private String watchName;
    private Integer unitPrice;
    private String discount;

    public Watch() { }

    public Watch(String watchId, String watchName, Integer unitPrice, String discount) {
        this.watchId = watchId;
        this.watchName = watchName;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }

    public String getWatchId() {
        return watchId;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public String getDiscount() {
        return discount;
    }
}

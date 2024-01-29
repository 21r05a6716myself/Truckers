package panday.truckers.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.Date;
import java.util.UUID;

@Entity
public class TruckLoadInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID loadId;
    @Column(nullable = false)
    private String loadingPoint;
    @Column(nullable = false)
    private String unloadingPoint;
    @Column(nullable = false)
    private String productType;
    @Column(nullable = false)
    private String truckType;
    @Column(nullable = false)
    private int weight; // In Tons
    @Column(nullable = false)
    private String shipperId;
    @Column(nullable = false)
    private Date date;
    private String comment;

    protected TruckLoadInfo() {

    }
    public TruckLoadInfo(String loadingPoint, String unloadingPoint, String productType, String truckType, int weight, String shipperId, Date date) {
        this.loadingPoint = loadingPoint;
        this.unloadingPoint = unloadingPoint;
        this.productType = productType;
        this.truckType = truckType;
        this.weight = weight;
        this.shipperId = shipperId;
        this.date = date;
    }
    public TruckLoadInfo(String loadingPoint, String unloadingPoint, String productType, String truckType, int weight, String shipperId, Date date, String comment) {
        this.loadingPoint = loadingPoint;
        this.unloadingPoint = unloadingPoint;
        this.productType = productType;
        this.truckType = truckType;
        this.weight = weight;
        this.shipperId = shipperId;
        this.date = date;
        this.comment = comment;
    }

    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getShipperId() {
        return shipperId;
    }

    public void setShipperId(String shipperId) {
        this.shipperId = shipperId;
    }

    public UUID getLoadId() {
        return loadId;
    }

    public void setLoadId(UUID loadId) {
        this.loadId = loadId;
    }

    public String getUnloadingPoint() {
        return unloadingPoint;
    }

    public void setUnloadingPoint(String unloadingPoint) {
        this.unloadingPoint = unloadingPoint;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLoadingPoint() {
        return loadingPoint;
    }

    public void setLoadingPoint(String loadingPoint) {
        this.loadingPoint = loadingPoint;
    }
}

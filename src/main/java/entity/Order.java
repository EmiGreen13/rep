package entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name="orders")
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "drugId", nullable = false)
    private Integer drugId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "userId", nullable = false)
    private Integer userId;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="drugId", insertable = false, updatable = false)
    private
    Drug drug;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="userId", insertable = false, updatable = false)
    private
    User user;

    public Order(){}

    public Order(Integer drugId, Integer userId){
        this.setDrugId(drugId);
        this.setUserId(userId);
    }

    @Override
    public int hashCode(){
        return id;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Order){
            Order category = (Order) obj;
            if (category.getDrugId().intValue() == this.getDrugId().intValue() & category.getId() == this.id
                    & category.getUserId().intValue() == this.getUserId().intValue()){
                return true;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package doan.stores.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @Column(name = "status")
    private int status;

    @Column(name = "total")
    private Long total;

    @Column(name = "create_day")
    private Date createDay;

    @Column(name = "last_update")
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}

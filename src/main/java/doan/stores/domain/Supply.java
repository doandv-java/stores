package doan.stores.domain;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "supplies")
@Data
public class Supply{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "detail")
    private String detail;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "active")
    private int active;
    @Column(name = "deleted")
    private int deleted;
}

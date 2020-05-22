package doan.stores.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "detail")
    private String detail;

    @Column(name = "active")
    private int active;

//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @OneToMany(fetch = FetchType.EAGER)
    List<Product> products;
}

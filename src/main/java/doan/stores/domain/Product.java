package doan.stores.domain;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "producer")
    private String producer;

    @Column(name = "price")
    private double price;

    @Column(name = "category_id", insertable = false, updatable = false)
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @Column(name = "detail")
    private String detail;

    @Column(name = "image_link")
    private String imageLink;

    @Column(name = "os")
    private String os;

    @Column(name = "cpu")
    private String cpu;

    @Column(name = "ram")
    private String ram;

    @Column(name = "gpu")
    private String gpu;

    @Column(name = "screen")
    private String screen;

    @Column(name = "storage")
    private String storage;

    @Column(name = "weight")
    private double weight;

    @Column(name = "release_year")
    private String releaseYear;

    @Column(name = "deleted")
    private int deleted;
}

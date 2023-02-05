package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String title;
    public String description;
    public int price;
    public double discountPercentage;
    public double rating;
    public int stock;
    public String brand;
    public String category;
    public String thumbnail;

    @ElementCollection // 1
    @CollectionTable(name = "images", joinColumns = @JoinColumn(name = "id")) // 2
    @Column(name = "images") // 3
    public List<String> images;
};

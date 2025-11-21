package com.marketplace.backend.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "product")
    private String product;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "condition")
    private String condition;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "product",  fetch = FetchType.LAZY,  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> usersLike = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, orphanRemoval = true,  cascade = CascadeType.ALL)
    private List<Images> images =  new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Comments> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void addLikes(Likes likes){
        usersLike.add(likes);
        likes.setProduct(this);
    }

    public void removeLikes(Likes likes){
        usersLike.remove(likes);
        likes.setProduct(null);
    }

    public void addImage(Images image) {
        images.add(image);
        image.setProduct(this);
    }

    public void addComments(Comments comment) {
        comments.add(comment);
        comment.setProduct(this);
    }

    public void deleteComments(Comments  comment) {
        comments.remove(comment);
        comment.setProduct(null);
    }
}
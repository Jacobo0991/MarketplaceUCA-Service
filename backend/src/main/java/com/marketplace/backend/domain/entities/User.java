package com.marketplace.backend.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "role")
    private String role;

    @Column(name = "reviews_count", nullable = false)
    private Integer reviewsCount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Likes> likedProducts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Token> tokens = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Comments> comments = new ArrayList<>();

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Review> writtenReviews = new ArrayList<>();

    @OneToMany(mappedBy = "reviewee", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Review> receivedReviews = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    //GetUserName and GetPassword are overridden

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addLikes(Likes likes){
        likedProducts.add(likes);
        likes.setUser(this);
    }

    public void removeLikes(Likes likes){
        likedProducts.remove(likes);
        likes.setUser(null);
    }

    public void addToken(Token token) {
        tokens.add(token);
        token.setUser(this);
    }

    public void addProduct(Product product) {
        products.add(product);
        product.setUser(this);
    }

    public void addComments(Comments comment){
        comments.add(comment);
        comment.setUser(this);
    }

    public void deleteComments(Comments comment){
        comments.remove(comment);
        comment.setUser(null);
    }

    public void addWrittenReview(Review review){
        writtenReviews.add(review);
        this.reviewsCount = writtenReviews.size();
        review.setReviewer(this);
    }

    public void deleteWrittenReview(Review review){
        writtenReviews.remove(review);
        this.reviewsCount = writtenReviews.size();
        review.setReviewer(null);
    }

    public void addReceivedReview(Review review){
        receivedReviews.add(review);
        review.setReviewee(this);
    }

    public double getAverageReceivedRating() {
        return receivedReviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }
}

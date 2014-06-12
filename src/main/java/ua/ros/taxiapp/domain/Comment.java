package ua.ros.taxiapp.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "comments")
@NamedQueries({
        @NamedQuery(name = "comment.with.customer", query = "from Comment c where c.customer = :customer"),
        @NamedQuery(name = "comment.with.taxist", query = "from Comment c where c.taxist = :taxist")
})

public class Comment implements Serializable {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue
    private Integer commentId;
    @ManyToOne
    @JoinColumn(name = "taxist_id", nullable = false)
    private Taxist taxist;
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Customer customer;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "text")
    private String text;

    public Comment() {
    }

    public Comment(Customer cust, Taxist taxist, Integer rating, String text) {
        this.customer = cust;
        this.taxist = taxist;
        this.rating = rating;
        this.text = text;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer newId) {
        commentId = newId;
    }

    public Taxist getTaxist() {
        return taxist;
    }

    public void setTaxist(Taxist taxist) {
        this.taxist = taxist;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String newText) {
        this.text = newText;
    }
}

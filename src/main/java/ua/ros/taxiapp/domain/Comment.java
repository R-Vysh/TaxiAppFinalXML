package ua.ros.taxiapp.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue
    Integer commentId;
    @ManyToOne
    @JoinColumn(name = "taxist_id", nullable = false)
    Taxist taxist;
    @ManyToOne
    @PrimaryKeyJoinColumn
    Customer customer;
    @Column(name = "rating")
    Integer rating;
    @Column(name = "text")
    String text;
    
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

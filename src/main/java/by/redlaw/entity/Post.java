package by.redlaw.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

/**
 * Created by ZheynovVV on 07.09.2016.
 */

@Entity
@Table(name = "post")
public class Post implements Serializable {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postID;

    @Column(name = "post_date")
    private Date postDate;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userID;

    @Column(name = "text", length = 256)
    private String text;

    @Column(name = "photo")
    private byte[] photo;

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long post_id) {
        this.postID = post_id;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserEntity getUserID() {
        return userID;
    }

    public void setUserID(UserEntity user_id) {
        this.userID = user_id;
    }


}

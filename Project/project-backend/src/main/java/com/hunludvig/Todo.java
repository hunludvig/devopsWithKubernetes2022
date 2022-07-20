package com.hunludvig;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Size;

@Entity
public class Todo implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private BigInteger id;

    @Size(max = 140)
    private String content;

    private ZonedDateTime createdAt;

    private Status status = Status.TODO;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status { TODO, DONE }
}

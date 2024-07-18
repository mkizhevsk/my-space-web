package com.mk.myspaceweb.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "authorityId")
public class Authority {

    @EmbeddedId
    private AuthorityId authorityId;

    @ManyToOne
    @MapsId("username")
    @JoinColumn(name = "username", insertable = false, updatable = false)
    private User user;

}
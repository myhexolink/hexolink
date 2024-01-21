//package com.auth.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Table(name = "avatars")
//@Access(AccessType.FIELD)
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class Avatar {
//
//    @Id
//    @Column(name = "user_id")
//    private Integer userId;
//    @Basic
//    @Column(name = "avatar", nullable = false)
//    private byte[] avatar;
//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "user_id")
//    @JsonBackReference
//    private User user;
//}

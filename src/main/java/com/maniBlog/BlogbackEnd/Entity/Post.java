package com.maniBlog.BlogbackEnd.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Table(
//        name = "posts",
//        uniqueConstraints = {
//                @UniqueConstraint(
//                        columnNames = {"title"}
//                )
//        }
//)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false,length = 100)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "content")
    private String content;
}

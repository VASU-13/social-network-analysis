package com.vasu.sna.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer friendshipId;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user1;

    @ManyToOne
    @JoinColumn(name="friendId")
    private User user2;

}

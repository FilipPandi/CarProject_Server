package com.zadaca.zadacaprojekt.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producerRecord")
public class ProducerMessenger {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producer_seq")
    @SequenceGenerator(name = "producer_seq", allocationSize = 10)
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "receiver_id")
    private Long receiverId;

    @ManyToOne
    @JoinColumn(name= "sender_user_id")
    User user;
}

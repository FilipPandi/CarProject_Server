package com.zadaca.zadacaprojekt.dto;


import com.zadaca.zadacaprojekt.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProducerMessengerDTO {

    private Long id;

    private String message;

    private Long receiverId;

    private UserDTO userDTO;

    public ProducerMessengerDTO(Long id, String message, Long receiverId, User user) {
        this.id = id;
        this.message = message;
        this.receiverId = receiverId;
        this.userDTO = new UserDTO(user.getUsername(), user.getPassword());
    }
}

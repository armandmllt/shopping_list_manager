package com.application.shoppinglistmanager.users;

import com.application.shoppinglistmanager.shopping_lists.ShoppingLists;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user")
    private Integer id;

    private String name;

    private String email;

    private String password;

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private ShoppingLists shoppingList;

    //id-free creator
    public Users (String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

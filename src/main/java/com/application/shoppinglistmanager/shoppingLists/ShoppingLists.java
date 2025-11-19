package com.application.shoppinglistmanager.shoppingLists;

import com.application.shoppinglistmanager.users.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class ShoppingLists {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_list")
    private Integer idList;

    @OneToOne //Add CASCADE-TYPE + FetchType ?
    @JoinColumn(name="fk_user", referencedColumnName = "id_user")
    
    private Users fkUser;

    //id-free creator
    public ShoppingLists (Users user) {
        this.fkUser = user;
    }
}

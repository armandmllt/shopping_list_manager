package com.application.shoppinglistmanager.shoppingLists;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name="fk_user")
    private Integer fkUser;

    //id-free creator
    public ShoppingLists (Integer fkUser) {
        this.fkUser = fkUser;
    }
}

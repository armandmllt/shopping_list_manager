package com.application.shoppinglistmanager.shopping_lists;

import java.util.List;

import com.application.shoppinglistmanager.shopping_list_recipes.ShoppingListRecipes;
import com.application.shoppinglistmanager.users.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"shoppingListRecipes"})
public class ShoppingLists {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_list")
    private Integer idList;

    @OneToOne //Add CASCADE-TYPE + FetchType ?
    @JoinColumn(name="fk_user", referencedColumnName = "id_user")
    private Users user;

    @OneToMany(mappedBy = "shoppingList")
    private List<ShoppingListRecipes> shoppingListRecipes;

    //id-free creator
    public ShoppingLists (Users user) {
        this.user = user;
    }
}

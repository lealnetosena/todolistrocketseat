package br.com.joseleal.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Modificador
 * public, private, protected
 * 
 * 
 * class, interface, enum
 */

@RestController
@RequestMapping("/users")
public class UserController {
    /**
     * String (texto)
     * Integer (int) numeros inteiros
     * Double (double) Números 0.0000
     * Float (float) Números 0.0000 - maior numero de casas decimais
     * char (A C)
     * Date (data)
     * void
     */
    
    @PostMapping("/")
    public void create(@RequestBody UserModel userModel){
        System.out.println(userModel.name);
    }
}

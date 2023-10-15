package br.com.joseleal.todolistrocketseat.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.joseleal.todolistrocketseat.user.IUserRepository;
// import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
// import jakarta.servlet.ServletRequest;
// import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// O servlet é a base para qualquer framework em java
// É a notação mais generica para gerenciar componentes
@Component

public class FilterTaskAuth extends OncePerRequestFilter {
    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();
        System.out.println("path");
        System.out.println(servletPath);
        if (servletPath.startsWith("/tasks/")) {

            // Pegar a autenticacao (usuario e senha)
            var authorization = request.getHeader("Authorization");
            // Validar
            if (authorization == null) {
                System.out.println("E NULO");
                response.sendError(401);
            } else {
                System.out.println("authorization");
                var authEncoded = authorization.substring("Basic".length()).trim();
                byte[] authDecode = Base64.getDecoder().decode(authEncoded);

                var authString = new String(authDecode);

                String[] credentials = authString.split(":");
                String username = credentials[0];
                String password = credentials[1];

                var user = this.userRepository.findByUsername(username);

                if (user == null) {
                    response.sendError(401);

                } else {
                    var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

                    if (passwordVerify.verified) {
                        request.setAttribute("idUser", user.getId());
                        filterChain.doFilter(request, response);
                    } else {
                        response.sendError(401);
                    }

                }
            }

        } else {
            filterChain.doFilter(request, response);
        }
    }

}

// Ao usar o Filter nos teriamos que converter toda requisição para HttpServelet
// public class FilterTaskAuth implements Filter {

// @Override
// public void doFilter(ServletRequest request, ServletResponse response,
// FilterChain chain)
// throws IOException, ServletException {
// // Executar ação
// // Atalho Print Sysout
// System.out.println("Cheguei no filrto");
// chain.doFilter(request, response);
// }

// }

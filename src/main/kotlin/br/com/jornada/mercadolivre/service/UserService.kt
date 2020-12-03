package br.com.jornada.mercadolivre.service

import br.com.jornada.mercadolivre.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
        private final val usuarioRepository: UsuarioRepository
): UserDetailsService {


    override fun loadUserByUsername(login: String): UserDetails {
        val usuarioBuscadoPeloLogin = usuarioRepository.findByLogin(login)
        if (usuarioBuscadoPeloLogin == null){
            return throw UsernameNotFoundException("Usuario não achado para o login informado: $login")
        }

        return usuarioBuscadoPeloLogin
    }
}
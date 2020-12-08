package br.com.jornada.mercadolivre.configuration.security

import br.com.jornada.mercadolivre.configuration.security.jwt.JwtTokenFilter
import br.com.jornada.mercadolivre.configuration.security.jwt.TokenManager
import br.com.jornada.mercadolivre.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
        @Autowired private val userService: UserService,
        @Autowired private val tokenManager: TokenManager
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun encode(): PasswordEncoder{
        return BCryptPasswordEncoder()
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                    .antMatchers("/actuator/**").permitAll()
                    .antMatchers("/actuator/prometheus").permitAll()
                    .antMatchers("/swagger-ui.html","/swagger-ui/**", "/v3/api-docs/**").permitAll()
                    .antMatchers(HttpMethod.POST,"/v1/usuarios").permitAll()
                    .antMatchers(HttpMethod.POST,"/v1/login").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .csrf().disable()
                    .httpBasic().disable()
                    .formLogin().disable()
                    .addFilterBefore(JwtTokenFilter(tokenManager), UsernamePasswordAuthenticationFilter::class.java)

    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userService).passwordEncoder(BCryptPasswordEncoder())
    }


}
package com.zlotluk.MaPSP.Security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.zlotluk.MaPSP.model.Userr;
import com.zlotluk.MaPSP.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService service;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("css/*.css", "js/*.js", "/").hasAnyAuthority("USER", "ADMIN")
				.antMatchers("/tokens").hasAuthority("ADMIN").anyRequest().authenticated().and().formLogin().permitAll()
				.and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/403").and().csrf().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		Userr u, a;
		u = service.first();
		a = service.adm();
		List<UserDetails> lu = new ArrayList<UserDetails>();
		lu.add(User.withUsername(u.getUser()).password(u.getPass()).authorities("USER").build());
		lu.add(User.withUsername(a.getUser()).password(a.getPass()).authorities("ADMIN").build());
		return new InMemoryUserDetailsManager(lu);
	}

}
package com.revature.aw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends ResourceServerConfigurerAdapter {

	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.authenticationManager(authenticationManager);
	}
}

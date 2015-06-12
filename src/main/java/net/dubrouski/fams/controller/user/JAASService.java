package net.dubrouski.fams.controller.user;

import java.security.Principal;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.inject.Named;

@Named(value = "jaasService")
@Stateful
public class JAASService {

	@Resource
	private SessionContext context;

	public Principal getCurrentUser() {
		return context.getCallerPrincipal();		
		
	}
}

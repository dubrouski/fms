package net.dubrouski.fams.test.runas;

import java.util.concurrent.Callable;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;

@Stateless
@RunAs("persAdmin")
@PermitAll
public class PersonAdminRunAs {
	public <V> V call(Callable<V> callable) throws Exception {
		return callable.call();
	}
}

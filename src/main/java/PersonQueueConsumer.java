import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RunAs;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.annotation.security.RunAsPrincipal;
import org.jboss.annotation.security.SecurityDomain;

import net.dubrouski.fams.controller.contract.bulkclose.ContractStateChangeEvent;
import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.User;
import net.dubrouski.fams.model.enums.UserRoles;
import net.dubrouski.fams.service.UserService;

@MessageDriven(mappedName = "personQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/personQueue"),
		@ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "true") })
@SecurityDomain("FmsSecurityDomain")
@RunAs("usermoduleAdmin")
@RunAsPrincipal("technical_user")
public class PersonQueueConsumer implements MessageListener {

	@Inject
	UserService uService;

	@Inject
	Logger logger;

	@Override
	public void onMessage(Message message) {
		try {
			Person payload = message.getBody(Person.class);
			logger.info("Received message from personQueue: "
					+ payload.toString());
			User u = new User();
			u.setUsername("C" + payload.getCode());
			u.setPerson(payload);
			u.setUserRoles(Arrays.asList(uService
					.getUserRoleByType(UserRoles.client)));
			// TODO resolve password setting and distribution!
			u.setPassword("default");

			uService.saveUser(u);
			logger.info("Created default user: "
					+ u.toString());
		} catch (JMSException e) {
			logger.log(Level.WARNING, "Error occured on message receive");
		}
	}

}

package org.practise.ejb;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.practise.ejb.service.CarService;

public class SampleClientEJB {

	public static void main(String[] args) throws Exception {
		try {
			accessEJB();
		} catch (Exception e) {
			System.err.println("Exception occured " + e.getMessage());
		}
	}

	private static void accessEJB() throws NamingException {

		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.enterprise.naming.SerialInitContextFactory");
		props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");

		//port information could be found in GF admin console
		props.setProperty("org.omg.CORBA.ORBInitialPort", "51037");

		final InitialContext context = new InitialContext(props);

		CarService carService = (CarService) context
				.lookup("java:global/ejb-remote-ex-1.0/CarServiceImpl");
		String str = carService.annualService();
		System.out.println(str);
	}
}

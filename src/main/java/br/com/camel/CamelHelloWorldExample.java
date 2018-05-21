package br.com.camel;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelHelloWorldExample {

	public static void main(String[] args) throws Exception {
		
		/**
		 * Exemplo 1 apache camel
		 */
//		CamelContext context = new DefaultCamelContext();
//		try {
//			context.addComponent("activemq", ActiveMQComponent.activeMQComponent("vm://localhost?broker.persistent=false"));
//			context.addRoutes(new RouteBuilder() {
//				@Override
//				public void configure() throws Exception {
//					from("activemq:queue:test.queue")
//					.to("stream:out");
//				}
//			});
//			ProducerTemplate template = context.createProducerTemplate();
//			context.start();
//			template.sendBody("activemq:test.queue", "Hello World");
//			Thread.sleep(2000);
//		} finally {
//			context.stop();
//		}
		
		/**
		 * Exemplo com spring framework
		 */
		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);
		try {
			ProducerTemplate template = camelContext.createProducerTemplate();
			camelContext.start();
			template.sendBody("activemq:test.queue", "Hello World");
		} finally {
			camelContext.stop();
		}
	}

}

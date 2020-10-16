package com.achila.sendemailalert;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableRabbit
public class SendEmailAlertApplication {

	public static final String EXCHANGE_NAME = "alert_send";
	public static final String DEFAULT_PARSING_QUEUE = "default_parser_q";
	public static final String ROUTING_KEY = "alerts";

	public static void main(String[] args) {
		SpringApplication.run(SendEmailAlertApplication.class, args);
	}

	@Bean
	public TopicExchange messageExchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}

	@Bean
	public Queue defaultParsingQueue() {
		return new Queue(DEFAULT_PARSING_QUEUE);
	}

	@Bean
	public Binding queueToExchangeBinding() {
		return BindingBuilder.bind(defaultParsingQueue()).to(messageExchange()).with(ROUTING_KEY);
	}

	@Bean
	public MessageConverter jsonMessageConverter(){
		return new Jackson2JsonMessageConverter();
	}
}

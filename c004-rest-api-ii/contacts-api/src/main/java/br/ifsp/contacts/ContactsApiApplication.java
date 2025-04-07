package br.ifsp.contacts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Contacts API", version = "1.0", description = "Documentação da API de Contatos"))
@SpringBootApplication
public class ContactsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactsApiApplication.class, args);
	}

}

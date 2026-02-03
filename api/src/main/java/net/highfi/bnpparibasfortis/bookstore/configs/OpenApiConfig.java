package net.highfi.bnpparibasfortis.bookstore.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI openAPI() {
    Contact contact = new Contact();
    contact.setEmail("highfi@highfi.net");
    contact.setName("");
    contact.setUrl("https://www.highfi.net/");

    Info info = new Info()
        .title("KATA - DISTRIBUTED DEVELOPER -  Online Bookstore API")
        .version("0.0.1")
        .contact(contact)
        .termsOfService("https://www.highfi.net/terms")
        .description(
            "This is a simple code kata that involves creating a basic front-end in React and a back-end using a RESTful API. We will create a Simple Online Bookstore. We will display a list of books and users will have the possibility to add books to their cart, display the cart and modify the quantity of items and remove items from the cart. ");

    ExternalDocumentation externalDocumentation = new ExternalDocumentation()
        .description("SpringShop Wiki Documentation")
        .url("https://springshop.wiki.github.org/docs");

    return new OpenAPI().info(info).externalDocs(externalDocumentation);
  }
}

package br.com.dbc.cooperativa;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class CooperativaApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(CooperativaApplication.class, args);
        try {
            Environment env = ctx.getEnvironment();

            String contextPath = env.getProperty("server.servlet.contextPath");
            String port = env.getProperty("server.port");

            log.info("\n\n *** \n\n"
                            + "\tAplicacao {} iniciada com sucesso!\n"
                            + "\tDisponivel nos enderecos:\n"
                            + "\tLocal: http://localhost:{}\n"
                            + "\tExterno: http://{}:{}\n"
                            + "\tSwagger Url: http://{}:{}\n"
                            + "\tLocal Swagger Url: http://localhost:{}\n"
                            + "\n *** \n\n",
                    env.getProperty("spring.application.name"),
                    port + contextPath,
                    InetAddress.getLocalHost().getHostAddress(),
                    port + contextPath,
                    InetAddress.getLocalHost().getHostAddress(),
                    port + contextPath + "swagger-ui.html",
                    port + contextPath + "swagger-ui.html");

        } catch (UnknownHostException e) {
            log.error("Falha ao executar aplicacao: {0}", e);
            ctx.close();
        }
    }
}
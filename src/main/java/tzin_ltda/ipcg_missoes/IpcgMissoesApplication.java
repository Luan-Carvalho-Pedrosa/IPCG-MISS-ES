package tzin_ltda.ipcg_missoes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IpcgMissoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpcgMissoesApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(@Value("${server.port}") String port, 
											@Value("${abrir.navegador:true}") boolean abrirNavegador) {
		return args -> {
			if (abrirNavegador) {
				String url = "http://localhost:" + port;
				String os = System.getProperty("os.name").toLowerCase();
				String command;

				if (os.contains("win")) {
					command = "cmd /c start firefox " + url;
				} else if (os.contains("mac")) {
					command = "open -a Firefox " + url;
				} else {
					command = "firefox " + url;
				}

				Runtime.getRuntime().exec(command);
			}
		};
	}

}

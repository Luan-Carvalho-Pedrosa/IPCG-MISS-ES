package tzin_ltda.ipcg_missoes.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper ModelMapper() {
        return new ModelMapper();

        
    }

    
}

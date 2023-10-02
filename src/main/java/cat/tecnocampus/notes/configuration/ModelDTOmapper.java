package cat.tecnocampus.notes.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelDTOmapper {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

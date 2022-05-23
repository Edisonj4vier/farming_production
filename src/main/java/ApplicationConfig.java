
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Para mapear DTO con Entidades
//DAO DATA ACCESS OBJECT
// DTO DATA TRANSFER OBJECT
@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}

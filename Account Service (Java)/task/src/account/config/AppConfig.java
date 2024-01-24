package account.config;



import account.entity.AppUser;
import account.entity.UserSignupDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {


    @Bean
    public ModelMapper modelMapper(PasswordEncoder passwordEncoder) {
        final ModelMapper modelMapper = new ModelMapper();
    Converter<String, String> passwordConverter
            = ctx -> (ctx.getSource() == null)
            ? null
            : passwordEncoder.encode(ctx.getSource());



    modelMapper.createTypeMap(UserSignupDTO.class, AppUser.class)
            //       .setProvider(clientProvider)
            .addMappings(mapping -> mapping
                    .using(passwordConverter)
                    .map(UserSignupDTO::getPassword, AppUser::setPassword));

        return modelMapper;
    }


}

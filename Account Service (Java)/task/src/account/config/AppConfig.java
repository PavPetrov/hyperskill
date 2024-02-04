package account.config;


import account.entity.AppUser;
import account.entity.DTO.PaymentDTO;
import account.entity.DTO.PaymentSummaryDTO;
import account.entity.DTO.UserSignupDTO;
import account.entity.Payment;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Configuration
public class AppConfig {


    @Bean
    public ModelMapper modelMapper(PasswordEncoder passwordEncoder) {
        final ModelMapper modelMapper = new ModelMapper();

        Converter<String, String> passwordConverter
                = ctx -> (ctx.getSource() == null)
                ? null
                : passwordEncoder.encode(ctx.getSource());


        Converter<String, YearMonth> monthYearConverter
                = ctx -> (ctx.getSource() == null)
                ? null
                : YearMonth.parse(ctx.getSource(), DateTimeFormatter.ofPattern("MM-yyyy"));


        modelMapper.createTypeMap(UserSignupDTO.class, AppUser.class)
                .addMappings(mapping -> mapping
                        .using(passwordConverter)
                        .map(UserSignupDTO::getPassword, AppUser::setPassword));

        modelMapper.createTypeMap(PaymentDTO.class, Payment.class)
                .addMappings(mapping -> mapping
                        .using(monthYearConverter)
                        .map(PaymentDTO::getPeriod, Payment::setPeriod))
                .addMappings(mapping -> mapping
                        .map(PaymentDTO::getEmail, Payment::setEmployee));

        //.setName(userApp.getName())
        //                .setLastname(userApp.getLastname())
        //                .setPeriod(periodDate)
        //                .setSalary(payment.getSalary()));

        modelMapper.createTypeMap(Payment.class, PaymentSummaryDTO.class)
                .addMappings(mapper -> mapper
                        .map(src -> src.getEmployee().getName(),PaymentSummaryDTO::setName))
                .addMappings(mapper -> mapper
                        .map(src -> src.getEmployee().getLastname(),PaymentSummaryDTO::setLastname));
        return modelMapper;
    }


}

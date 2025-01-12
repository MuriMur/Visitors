package org.visitors.configurations;

import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/index").setViewName("home");
        registry.addViewController("/roles").setViewName("roles_view");
        registry.addViewController("/visitors").setViewName("visitors_view");
        registry.addViewController("/users").setViewName("users_view");
        registry.addViewController("/appointments").setViewName("appointments_view");
        registry.addViewController("/registries").setViewName("registries_view");
        registry.addViewController("/waitinglist").setViewName("waitinglist_view");
        registry.addViewController("/editprofile").setViewName("editprofile");
        registry.addViewController("/checkPatient").setViewName("check_view");
        registry.addViewController("/checkPatient/startCheck").setViewName("check_view");
        registry.addViewController("/checkPatient/finishCheck").setViewName("waitinglist_view");
        registry.addViewController("/checkPatient/returnToWaitingList").setViewName("waitinglist_view");
        registry.addViewController("/callPatient").setViewName("check_view");
    }

    @Bean
    public FormattingConversionService conversionService() {

        // Use the DefaultFormattingConversionService but do not register defaults
        DefaultFormattingConversionService conversionService =
                new DefaultFormattingConversionService(false);

        // Ensure @NumberFormat is still supported
        conversionService.addFormatterForFieldAnnotation(
                new NumberFormatAnnotationFormatterFactory());

        // Register JSR-310 date conversion with a specific global format
        DateTimeFormatterRegistrar dateTimeRegistrar = new DateTimeFormatterRegistrar();
        dateTimeRegistrar.setDateFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dateTimeRegistrar.registerFormatters(conversionService);

        // Register date conversion with a specific global format
        DateFormatterRegistrar dateRegistrar = new DateFormatterRegistrar();
        dateRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
        dateRegistrar.registerFormatters(conversionService);

        return conversionService;
    }
}
package com.dc.base.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSwagger
public class SwaggerConfig
{
    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) { this.springSwaggerConfig = springSwaggerConfig; }



    @Bean
    public SwaggerSpringMvcPlugin customImplementation() { return (new SwaggerSpringMvcPlugin(this.springSwaggerConfig))
            .apiInfo(apiInfo())
            .includePatterns(new String[] { ".*?" }); }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "coding_MobileShop",
                "My Apps API Description",
                "My Apps API terms of service",
                "My Apps API Contact Email",
                "My Apps API Licence Type",
                "My Apps API License URL");
        return apiInfo;
    }
}

package com.bbmore.order.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);

        // PropertyMap을 사용하여 필드 매핑 정의
        modelMapper.addMappings(new PropertyMap<Order, OrderDTO>() {
            @Override
            protected void configure() {
                // source.getPhone()을 destination.getPhone()에 매핑
                // 필드명이 같은 경우 자동으로 매핑되지만, 명시적으로 매핑할 수 있습니다.
                using(ctx -> ctx.getSource() != null ? ctx.getSource().toString() : null)
                        .map(source.getRecipientPhone(), destination.getRecipientPhone());
            }
        });

        return modelMapper;
    }
}

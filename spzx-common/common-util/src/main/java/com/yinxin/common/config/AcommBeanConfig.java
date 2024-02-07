//package com.yinxin.common.config;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.yinxin.common.utils.IocUtil;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//
///**
// * @author ankelen
// * @date 2022-10-15 12:12
// */
//@Configuration
//public class AcommBeanConfig {
//    @Bean
//    @Lazy(value = false)
//    @ConditionalOnMissingBean
//    IocUtil iocUtil() {
//        return IocUtil.getInstance();
//    }
//
//    @Configuration
//    static class WebMvcConfig {
//        @Bean
//        Filter corsFilter() {
//            return new CorsFilter();
//        }
//
//        @Bean
//        Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
//            return builder -> builder
//                    .serializerByType(LocalDateTime.class, localDateTimeJsonSerializer())
//                    .deserializerByType(LocalDateTime.class, localDateTimeJsonDeserializer());
//        }
//
//        /**
//         * NOTICE: 注意使用 秒级 时间戳
//         */
//        private JsonSerializer<LocalDateTime> localDateTimeJsonSerializer() {
//            return new JsonSerializer<LocalDateTime>() {
//                @Override
//                public void serialize(LocalDateTime dateTime, JsonGenerator generator, SerializerProvider provider) throws IOException {
//                    if (dateTime != null) {
//                        long epochSecond = dateTime.toEpochSecond(ZoneOffset.ofHours(8));
//                        generator.writeNumber(epochSecond);
//                    }
//                }
//            };
//        }
//
//        /**
//         * NOTICE: 注意使用 秒级 时间戳
//         */
//        private JsonDeserializer<LocalDateTime> localDateTimeJsonDeserializer() {
//            return new JsonDeserializer<LocalDateTime>() {
//                @Override
//                public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
//                    long epochSecond = parser.getValueAsLong();
//                    return LocalDateTime.ofEpochSecond(epochSecond, 0, ZoneOffset.ofHours(8));
//                }
//            };
//        }
//
//        /**
//         * 跨域过滤器
//         */
//        static class CorsFilter implements Filter {
//            @Override
//            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//                HttpServletRequest req = (HttpServletRequest) request;
//                HttpServletResponse res = (HttpServletResponse) response;
//
//                // add cors header
//                res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
//                res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
//                res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*");
//                res.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
//                // skip OPTIONS request
//                if (req.getMethod().equals(HttpMethod.OPTIONS.name())) {
//                    return;
//                }
//                chain.doFilter(request, response);
//            }
//        }
//    }
//}

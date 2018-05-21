package org.web3j.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.web3j.aspect.RequestContentLoggerAspect;

/**
 * Created by Dayong on 16/9/15.
 */
@EnableAutoConfiguration
@Configuration
//@ImportResource({"classpath*:spring-dubbo.xml"}) //加入dubbo的配置文件
//@JsonComponent
@EnableWebMvc
public class AppConfiguration extends WebMvcConfigurerAdapter
{
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //注册拦截器
//        registry.addInterceptor(new RequestInterceptor());
//        super.addInterceptors(registry);
//    }

    /**
     * Config request logger aspect
     *
     * @return the log aspect
     */
    @Bean
    public RequestContentLoggerAspect getLogAspect() {
        return new RequestContentLoggerAspect();
    }
    //配合@JsonComponent使用
    /*public static class Serializer extends JsonSerializer<SomeObject> {
    }

    public static class Deserializer extends JsonDeserializer<SomeObject> {
    }*/


    /*@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //配置对象转化器,这里使用FastJson来转换
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //支持的媒体类型
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(new MediaType("text/html;charset=UTF-8"));
        list.add(new MediaType("application/json;charset=UTF-8"));
        list.add(new MediaType("multipart/form-data;"));
        converter.setSupportedMediaTypes(list);
        //序列化的功能设置
        SerializerFeature[] features = {
                WriteNullBooleanAsFalse,
                WriteNullStringAsEmpty,
                WriteMapNullValue,
                QuoteFieldNames
        };
        converter.setFeatures(features);
        converters.add(converter);
    }*/


    /*@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**");
            }
        };
    }*/

    /*@Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        //设置启动端口
        factory.setPort(9000);
        //设置会话超时时间
        factory.setSessionTimeout(10, TimeUnit.MINUTES);
        //设置404页面
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
        return factory;
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        String prefix = "/WEB-INF/jsp/";
        String suffix = ".jsp";
        //设置全局视图资源文件的路径
        return new InternalResourceViewResolver(prefix, suffix);
    }*/
}

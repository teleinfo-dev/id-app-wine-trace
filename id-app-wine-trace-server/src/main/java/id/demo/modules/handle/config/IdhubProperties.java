package id.demo.modules.handle.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wtt
 * @version 1.0
 * @date Created in 2024/7/17
 * @description
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "app.idhub")
public class IdhubProperties {

    /**
     * 企业节点地址
     */
    private String url;
    
    /**
     * 应用标识身份
     */
    private String handleUser;

    /**
     * 私钥
     */
    private String privateKey;
}

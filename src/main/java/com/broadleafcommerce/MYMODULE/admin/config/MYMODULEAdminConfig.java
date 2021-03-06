/*-
 * #%L
 * BroadleafCommerce MYMODULE
 * %%
 * Copyright (C) 2009 - 2017 Broadleaf Commerce
 * %%
 * Licensed under the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt).
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
 * 
 * NOTICE:  All information contained herein is, and remains
 * the property of Broadleaf Commerce, LLC
 * The intellectual and technical concepts contained
 * herein are proprietary to Broadleaf Commerce, LLC
 * and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Broadleaf Commerce, LLC.
 * #L%
 */
package com.broadleafcommerce.MYMODULE.admin.config;

import org.broadleafcommerce.common.admin.condition.ConditionalOnAdmin;
import org.broadleafcommerce.common.extensibility.context.merge.Merge;
import org.broadleafcommerce.presentation.condition.ConditionalOnTemplating;
import org.broadleafcommerce.presentation.resolver.BroadleafClasspathTemplateResolver;
import org.broadleafcommerce.presentation.resolver.BroadleafTemplateMode;
import org.broadleafcommerce.presentation.resolver.BroadleafTemplateResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;

/**
 * @author
 */
@ConditionalOnAdmin
@Configuration("blMYMODULEAdminConfig")
public class MYMODULEAdminConfig {
    
    @Merge(targetRef = "blMessageSourceBaseNames")
    public List<String> blMYMODULEMessages() {
        return Arrays.asList("classpath:/messages/MYMODULEMessages");
    }
    
    @ConditionalOnTemplating
    @Configuration
    public static class MYMODULEsAdminFrontendConfig {
        
        @Autowired
        protected Environment env;
        
        @Merge(targetRef = "blJsLocations")
        public List<String> blMYMODULEAdminJsLocations() {
            return Arrays.asList("classpath:/MYMODULE_style/js/");
        }
        
        @Merge(targetRef = "blJsFileList")
        public List<String> blMYMODULEAdminJsFileList() {
            return Arrays.asList("admin/MYMODULE.js");
        }
        
        @Bean
        public BroadleafTemplateResolver blAdminMYMODULEClasspathTemplateResolver() {
            BroadleafClasspathTemplateResolver resolver = new BroadleafClasspathTemplateResolver();
            resolver.setPrefix("MYMODULE_style/templates/admin/");
            resolver.setSuffix(".html");
            resolver.setTemplateMode(BroadleafTemplateMode.HTML);
            resolver.setCharacterEncoding("UTF-8");
            resolver.setCacheable(env.getProperty("cache.page.templates", Boolean.class));
            resolver.setCacheTTLMs(env.getProperty("cache.page.templates.ttl", Long.class));
            return resolver;
        }
    }

}

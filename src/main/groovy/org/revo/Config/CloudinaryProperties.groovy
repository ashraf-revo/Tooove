package org.revo.Config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * Created by ashraf on 09/10/16.
 */
@Component
@ConfigurationProperties("cloudinaryProperties")
class CloudinaryProperties {
    String cloud_name
    String api_key
    String api_secret
}
package com.bunsaned3thinking.mogu.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import com.bunsaned3thinking.mogu.post.repository.module.elasticsearch.PostDocElasticRepository;

import io.micrometer.common.lang.NonNullApi;

@NonNullApi
@EnableElasticsearchRepositories(basePackageClasses = PostDocElasticRepository.class)
@Configuration
public class ElasticSearchConfig extends ElasticsearchConfiguration {
	@Value("${elasticsearch.rest.uris}")
	private String elasticUrl;

	@Override
	public ClientConfiguration clientConfiguration() {
		return ClientConfiguration.builder()
			.connectedTo(elasticUrl)
			.build();
	}
}

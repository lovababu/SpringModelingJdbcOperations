package org.avol.springdao.config;

import org.avol.springdao.repository.TweetRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by Lovababu on 8/22/2015.
 */

@Configuration
public class AppConfig {


    /**
     * Spring provided H2 Embedded Database.
     * It Reads the dbscript and initiates the Database.
     *
     * @return
     */
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setName("H2-Test-DB");
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:tweet-schema.sql")
                .addScript("classpath:tweet-data.sql").build();
        System.out.println("Database initialization done.");
        return db;
    }

    @Bean
    public TweetRepository tweetRepository(){
        return new TweetRepository(dataSource());
    }
}

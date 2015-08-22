package org.avol.springdao;

import org.avol.springdao.config.AppConfig;
import org.avol.springdao.repository.TweetRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Lovababu on 8/22/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class SpringJdbcOperationsTest {

    @Autowired
    private TweetRepository tweetRepository;

    @Test
    public void testSqlQuery() {
        tweetRepository.sqlQueryTest("SELECT * FROM TWEET");
    }

    @Test
    public void testMappingSqlQuery() {
        tweetRepository.mappingSqlQueryTest("SELECT * FROM TWEET");
    }

    @Test
    public void testSqlUpdate() {
        tweetRepository.sqlUpdateTest("UPDATE TWEET SET MSG = ? WHERE ID = ?");
        tweetRepository.sqlQueryTest("SELECT * FROM TWEET");
    }
}

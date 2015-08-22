package org.avol.springdao.repository;

import org.avol.springdao.entity.Tweet;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

/**
 * Created by Lovababu on 8/22/2015.
 */
public class TweetRepository {

    private DataSource dataSource;

    public TweetRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Illustrate the how to subclass the SqlQuery abstract class.
     *
     * @param query
     */
    public void sqlQueryTest(String query) {
        System.out.println("------------------ SqlQuery Test ----------------------");
        SqlQuery sqlQuery = new SqlQuery(dataSource, query) {
            @Override
            protected RowMapper newRowMapper(Object[] parameters, Map context) {
                return new TweetRowMapper();
            }
        };

        List<Tweet> tweets =  sqlQuery.execute();
        printTweets(tweets);
        System.out.println("------------SqlQuery Test End---------------------------");
    }

    /**
     * Illustrate how to subclass the MappingSqlQuery abstract.
     *
     * @param query
     */
    public void mappingSqlQueryTest(String query) {
        System.out.println("------------------ MappingSqlQuery Test ----------------------");
        MappingSqlQuery mappingSqlQuery = new MappingSqlQuery(dataSource, query) {
            @Override
            protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                Tweet tweet = new Tweet();
                tweet.setId(rs.getInt("ID"));
                tweet.setMsg(rs.getString("MSG"));
                return tweet;
            }
        };
        //mappingSqlQuery.setSql("");
        List<Tweet> tweets = mappingSqlQuery.execute();
        printTweets(tweets);
        System.out.println("------------------ MappingSqlQuery Test ----------------------");

    }

    /**
     * Illustrates the SqlUpdate jdbc modelling class.
     *
     * @param query
     */
    public void sqlUpdateTest(String query){
        SqlUpdate sqlUpdate  = new SqlUpdate(dataSource, query);
        sqlUpdate.declareParameter(new SqlParameter(Types.VARCHAR));
        sqlUpdate.declareParameter(new SqlParameter(Types.NUMERIC));
        int c = sqlUpdate.update("Hello hello hello..", 1);
        System.out.println("No of records updated : " + c);
    }

    private void printTweets(List<Tweet> tweets) {
        System.out.println("Tweet data :");
        for (Tweet tweet : tweets) {
            System.out.println("Tweet Id: " + tweet.getId() + ", Message: " + tweet.getMsg());
        }
    }

    private class TweetRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Tweet tweet = new Tweet();
            tweet.setId(rs.getInt("ID"));
            tweet.setMsg(rs.getString("MSG"));
            return tweet;
        }
    }
}

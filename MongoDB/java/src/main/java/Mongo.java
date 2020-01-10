import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;

public class Mongo
{
    private static MongoDatabase db;

    private Mongo()
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDatabase("home");
    }

    // task 1a
    private List<String> getSortedCities()
    {
        List<String> result =
                db.getCollection("buisiness")
                .distinct("city", String.class)
                .into(new ArrayList<>());
        Collections.sort(result);
        return result;
    }

    // task 1b
    private long reviewsAfter2010()
    {
        Bson filter = (Filters.gte("date", "2011-01-01"));
        return db
                .getCollection("review")
                .countDocuments(filter);
    }

    // task 1c
    private List<Document> closedBusinesses()
    {
        return db
                .getCollection("buisiness")
                .find(eq("open", false))
                .projection(fields(include("name", "full_address", "stars"), excludeId()))
                .into(new ArrayList<>());
    }

    // task 1d
    private FindIterable<Document> usersWithVotes()
    {
        db.getCollection("user").createIndex(Indexes.ascending("name"));
        Bson filter = or(
                eq("votes.funny", 0),
                eq("votes.useful", 0)
        );
        return db
                .getCollection("user")
                .find(filter)
                .sort(ascending("name"));
    }

    // task 1e
    private AggregateIterable<Document> tipsFrom2012PerBusiness()
    {
        Bson match = match(Filters.regex("date", "2012"));
        Bson group = group("$business_id", Accumulators.sum("count", 1));
        Bson lookupOperation = lookup("buisiness", "_id", "business_id", "business_tip");
        Bson unwind = unwind("$business_data");
        Bson project = project(fields(include("business_tip.name", "count"), excludeId()));
        Bson sort = Aggregates.sort(ascending("count"));
        return db
                .getCollection("tip")
                .aggregate(Arrays.asList(match, group, lookupOperation, unwind, project, sort));
    }

    // task 1f
    private AggregateIterable<Document> avgStarsPerBusiness()
    {
        Bson match = match(Filters.gte("avgStars", 4));
        Bson group = group("$business_id", Accumulators.avg("avgStars", "$stars"));
        Bson lookupOperation = lookup("buisiness", "_id", "business_id", "business_review");
        Bson unwind = unwind("$business_review");
        Bson project = project(fields(include("business_review.name", "avgStars"), excludeId()));

        return db.getCollection("review")
                .aggregate(Arrays.asList(group, lookupOperation, unwind, project, match));
    }

    // task 1g
    private void deleteBusinessesWith2Stars()
    {
        db.getCollection("business").deleteMany(eq("stars", 2));
    }

    public static void main(String[] args)
    {
        Mongo mongo = new Mongo();

        // task 1a
/*
        List<String> citiesWithBusinessNames = mongo.getSortedCities();
        for (String cityName: citiesWithBusinessNames)
            System.out.println(cityName);
*/

        // task 1b
/*
        long reviewsFrom2011or2012count = mongo.reviewsAfter2010();
        System.out.println(reviewsFrom2011or2012count);
*/

        // task 1c
/*
        List<Document> businessInfo = mongo.closedBusinesses();
        for (Document business: businessInfo)
        {
            System.out.println(business.get("name"));
            System.out.println(business.get("full_address"));
            System.out.println(business.get("stars"));
            System.out.println("\n");
        }
*/

        // task 1d
        /*
        FindIterable<Document> usersWithVotesInfo = mongo.usersWithVotes();
        for(Document user: usersWithVotesInfo)
            System.out.println(user);
         */

        // task 1e
/*
        AggregateIterable<Document> tipsPerBusinessCount = mongo.tipsFrom2012PerBusiness();
        int i = 0;
        for(Document doc: tipsPerBusinessCount)
        {
            System.out.println(doc);
            i += 1;
            if (i == 10)
                break;
        }
*/

        // task 1f

        AggregateIterable<Document> avgStarsPerBusiness = mongo.avgStarsPerBusiness();
        int i = 0;
        for (Document doc: avgStarsPerBusiness)
        {
            System.out.println(doc);
            i += 1;
            if (i == 10)
                break;
        }


        // task 1g
        //mongo.deleteBusinessesWithStarsBelow3();
    }
}

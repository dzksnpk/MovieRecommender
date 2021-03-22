import org.apache.log4j.BasicConfigurator;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class MovieRecommender {

    public static void main(String[] args) {
        BasicConfigurator.configure();

        try{

            // FileDataModel class, suitable for small data sets - up to 10 million rows
            DataModel dataModel = new FileDataModel(new File("[yourdirectory]\\ratings2.csv"));

            // Writing recommendations to text file for test dataset.
            File file = new File("[yourdirectory]\\recommendation.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);

            // Recommender system with collaborative filtering based on User
            UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
            UserNeighborhood userNeighborhood = new ThresholdUserNeighborhood(0.2, userSimilarity, dataModel);
            UserBasedRecommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);

            //start benchmark
            long startTime = System.currentTimeMillis();

            //Write recommendations to file
            String writeToFile = "";
            for (LongPrimitiveIterator users = dataModel.getUserIDs(); users.hasNext();)
            {
                long userId = users.next();
                List<RecommendedItem> recommendations = recommender.recommend(userId, 5);
                writeToFile = userId + "[" ;
                for (RecommendedItem recommendation : recommendations)
                {
                    writeToFile =  writeToFile + recommendation.getItemID() +":" + recommendation.getValue() +",";

                }
                writeToFile = writeToFile + "]" + "\n";
                writer.write(writeToFile);

            }
            writer.flush();
            writer.close();

            //end benchmark
            long endTime = System.currentTimeMillis();

            //display elapsed time in milliseconds
            System.out.println("That took " + (endTime - startTime) + " milliseconds");

        } catch(Exception e)  {
            e.printStackTrace();
        }
    }
}


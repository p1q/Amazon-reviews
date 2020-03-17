package com.reviews.amazon;

//import org.springframework.boot.SpringApplication;
import com.reviews.amazon.service.CsvReader;
import com.reviews.amazon.service.impl.CsvReaderImpl;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class AmazonReviewsApplication {
    private static final Logger LOGGER = Logger.getLogger(AmazonReviewsApplication.class);

    public static void main(String[] args) throws IOException {
        //SpringApplication.run(AmazonReviewsApplication.class, args);
        CsvReader csvReader = new CsvReaderImpl();
        Optional<List> fileLines = csvReader.openFile();
        if (fileLines.isPresent()) {
            csvReader.getMostActiveUsers(fileLines.get());
            csvReader.getMostCommentedFoodItems(fileLines.get());
            csvReader.getMostUsedWordsInReviews(fileLines.get());
        } else {
            LOGGER.error("File open error!");
            throw new IOException("File open error!");
        }
    }
}

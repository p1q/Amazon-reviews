package com.reviews.amazon.service.impl;

import com.reviews.amazon.service.CsvReader;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class CsvReaderImplTest {
    @Test
    public void getMostActiveUsersShouldReturnSet() {
        SortedSet<String> expectedResult = new TreeSet<>();
        expectedResult.add("delmartian");
        expectedResult.add("dll pa");
        expectedResult.add("Karl");
        expectedResult.add("David C. Sullivan");
        expectedResult.add("Pamela G. Williams");
        expectedResult.add("Twoapennything");

        CsvReader csvReader = new CsvReaderImpl();
        SortedSet<String> actualResult = csvReader.getMostActiveUsers(getFileLines());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getMostCommentedFoodItemsShouldReturnSet() {
        SortedSet<String> expectedResult = new TreeSet<>();
        expectedResult.add("B000E7L2R4");
        expectedResult.add("B000LQOCH0");
        expectedResult.add("B000UA0QIQ");
        expectedResult.add("B001E4KFG0");
        expectedResult.add("B006K2ZZ7K");
        expectedResult.add("B00813GRG4");

        CsvReader csvReader = new CsvReaderImpl();
        SortedSet<String> actualResult = csvReader.getMostCommentedFoodItems(getFileLines());

        assertEquals(expectedResult, actualResult);
    }

    private List<String> getFileLines() {
        List<String> fileLines = new ArrayList<>();
        fileLines.add("Id,ProductId,UserId,ProfileName,HelpfulnessNumerator,HelpfulnessDenominator,Score,Time,Summary,Text");
        fileLines.add("1,B001E4KFG0,A3SGXH7AUHU8GW,delmartian,1,1,5,1303862400,Good Quality Dog Food,I have bought several of the Vitality canned dog food products and have found them all to be of good quality. The product looks more like a stew than a processed meat and it smells better. My Labrador is finicky and she appreciates this product better than  most.");
        fileLines.add("2,B00813GRG4,A1D87F6ZCVE5NK,dll pa,0,0,1,1346976000,Not as Advertised,\"Product arrived labeled as Jumbo Salted Peanuts...the peanuts were actually small sized unsalted. Not sure if this was an error or if the vendor intended to represent the product as \"\"Jumbo\"\".\"");
        fileLines.add("3,B000LQOCH0,ABXLMWJIXXAIN,delmartian,1,1,4,1219017600,\"\"\"Delight\"\" says it all\",\"This is a confection that has been around a few centuries.  It is a light, pillowy citrus gelatin with nuts - in this case Filberts. And it is cut into tiny squares and then liberally coated with powdered sugar.  And it is a tiny mouthful of heaven.  Not too chewy, and very flavorful.  I highly recommend this yummy treat.  If you are familiar with the story of C.S. Lewis' \"\"The Lion, The Witch, and The Wardrobe\"\" - this is the treat that seduces Edmund into selling out his Brother and Sisters to the Witch.\"");
        fileLines.add("4,B000UA0QIQ,A395BORC6FGVXV,Karl,3,3,2,1307923200,Cough Medicine,If you are looking for the secret ingredient in Robitussin I believe I have found it.  I got this in addition to the Root Beer Extract I ordered (which was good) and made some cherry soda.  The flavor is very medicinal.");
        fileLines.add("5,B006K2ZZ7K,A1UQRSCLF8GW1T,delmartian,0,0,5,1350777600,Great taffy,\"Great taffy at a great price.  There was a wide assortment of yummy taffy.  Delivery was very quick.  If your a taffy lover, this is a deal.\"");
        fileLines.add("6,B006K2ZZ7K,ADT0SRK1MGOEU,Twoapennything,0,0,4,1342051200,Nice Taffy,\"I got a wild hair for taffy and ordered this five pound bag. The taffy was all very enjoyable with many flavors: watermelon, root beer, melon, peppermint, grape, etc. My only complaint is there was a bit too much red/black licorice-flavored pieces (just not my particular favorites). Between me, my kids, and my husband, this lasted only two weeks! I would recommend this brand of taffy -- it was a delightful treat.\"");
        fileLines.add("7,B000LQOCH0,A1SP2KVKFXXRU1,David C. Sullivan,0,0,5,1340150400,Great!  Just as good as the expensive brands!,\"This saltwater taffy had great flavors and was very soft and chewy.  Each candy was individually wrapped well.  None of the candies were stuck together, which did happen in the expensive version, Fralinger's.  Would highly recommend this candy!  I served it at a beach-themed party and everyone loved it!\"");
        fileLines.add("8,B006K2ZZ7K,A3JRGQVEQN31IQ,Pamela G. Williams,0,0,5,1336003200,\"Wonderful, tasty taffy\",This taffy is so good.  It is very soft and chewy.  The flavors are amazing.  I would definitely recommend you buying it.  Very satisfying!!");
        fileLines.add("9,B000E7L2R4,A1MZYO9TZK0BBI,dll pa,1,1,5,1322006400,Yay Barley,Right now I'm mostly just sprouting this so my cats can eat the grass. They love it. I rotate it around with Wheatgrass and Rye too");
        fileLines.add("10,B00813GRG4,A21BT40VZCCYT4,delmartian,0,0,5,1351209600,Healthy Dog Food,This is a very healthy dog food. Good for their digestion. Also good for small puppies. My dog eats her required amount at every feeding.");
        return fileLines;
    }
}

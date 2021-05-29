package com.adeel.faceSketch;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.CreateCollectionRequest;
import com.amazonaws.services.rekognition.model.CreateCollectionResult;
import com.amazonaws.services.rekognition.model.ListCollectionsRequest;
import com.amazonaws.services.rekognition.model.ListCollectionsResult;
import java.util.List;


public class collection_create {
    public static void main(String[] args) throws Exception {
        
        AWSCredentials credentials = null;
    try {
         credentials = new ProfileCredentialsProvider("default").getCredentials();
    } catch (Exception e) {
         throw new AmazonClientException("Cannot load the credentials: ", e);
    }

  //      AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();
  //      rekognitionClient.setRegoin(Region.ap-south-1);        
        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion(Regions.AP_SOUTH_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

            String collectionId = "Records"; //Collection Name to be Created

            System.out.println("Creating collection: " + collectionId );

            CreateCollectionRequest request = new CreateCollectionRequest()
                        .withCollectionId(collectionId);

            CreateCollectionResult createCollectionResult = rekognitionClient.createCollection(request); 
            System.out.println("CollectionArn : " +
               createCollectionResult.getCollectionArn());
            System.out.println("Status code : " +
               createCollectionResult.getStatusCode().toString());
            
            
            
   }
}

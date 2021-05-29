/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adeel.faceSketch;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.Face;
import com.amazonaws.services.rekognition.model.ListFacesRequest;
import com.amazonaws.services.rekognition.model.ListFacesResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;


public class collection_list {
    public static final String collectionId = "Records"; //Collection Name

    public static void main(String[] args) throws Exception {
      
    //  AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

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

      ObjectMapper objectMapper = new ObjectMapper();

      ListFacesResult listFacesResult = null;
      System.out.println("Faces in collection " + collectionId);

      String paginationToken = null;
      do {
         if (listFacesResult != null) {
            paginationToken = listFacesResult.getNextToken();
         }
         
         ListFacesRequest listFacesRequest = new ListFacesRequest()
                 .withCollectionId(collectionId)
                 .withMaxResults(1)
                 .withNextToken(paginationToken);
        
         listFacesResult =  rekognitionClient.listFaces(listFacesRequest);
         List < Face > faces = listFacesResult.getFaces();
         for (Face face: faces) {
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter()
               .writeValueAsString(face));
         }
      } while (listFacesResult != null && listFacesResult.getNextToken() !=
         null);
   }
}

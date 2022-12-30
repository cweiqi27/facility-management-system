/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facilitymanagementsystem.entity;

import facilitymanagementsystem.adt.ArrayList;
import facilitymanagementsystem.adt.ListInterface;


/**
 *
 * @author Thomas
 */
public class Facility {
    private int facId;
    private String facName;
    private String facType;
    private String facVenue;
    private Review Review;
    private ListInterface <Review> reviewList;
    
    public Facility(){
        
    }
    
    public Facility(int facId, String facName, String facType, String facVenue){
        this.facId = facId;
        this.facName = facName;
        this.facType = facType;
        this.facVenue = facVenue;
        this.reviewList = new ArrayList<>();
    }
     public void addReview(Review r){
        reviewList.add(r);
    }

    public Facility(int facId, String facName, String facType, String facVenue, Review Review) {
        this.facId = facId;
        this.facName = facName;
        this.facType = facType;
        this.facVenue = facVenue;
        this.Review = Review;
    }
     
    public int getFacId(){
        return facId;
    }
    
    public void setFacId(int facId){
        this.facId = facId;
    }
    
    public String getfacName(){
       return facName;
    }
    
    public void setFacName(String facName){
        this.facName = facName;
    }
     
    public String getfacType(){
       return facType;
    }
    
    public void setFacType(String facType){
        this.facType = facType;
    }
    
     public String getfacVenue(){
       return facVenue;
    }
    
    public void setfacVenue(String facVenue){
        this.facVenue = facVenue;
    }
    
    public Review getReview(){
        return Review;
    }

    public void setReview(Review Review){
       this.Review = Review;
    }
    
    @Override
    public String toString() {
        return String.format("%-5d %-15s %-10s %-10s %-20s",facId,facName,facType,facVenue,Review);
    }
    
   
}

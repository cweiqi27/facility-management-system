/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facilitymanagementsystem.entity;

/**
 *
 * @author Chan Wei Qi
 */
public class Review {
  private int reviewId;
  private String reviewTitle;
  private String reviewContent;
  private reviewType type;
  private int rating;
//  private Booking booking;
//  private Facility facility;
//  private User user;
  
  public Review() {
  }
  
  public Review(int reviewId, String reviewTitle, String reviewContent, reviewType type, int rating) {
    this.reviewId = reviewId;
    this.reviewTitle = reviewTitle;
    this.reviewContent = reviewContent;
    this.type = type;
    this.rating = rating;
  }
  
  public int getreviewId() {
    return this.reviewId;
  }
  
  public String getReviewTitle() {
    return this.reviewTitle;
  }
  
  public String getReviewContent() {
    return this.reviewContent;
  }
  
  public reviewType getType() {
    return this.type;
  }
  
  public int getRating() {
    return this.rating;
  }
  
  public void setreviewId(int reviewId) {
    this.reviewId = reviewId;
  }
  
  public void setReviewTitle(String reviewTitle) {
    this.reviewTitle = reviewTitle;
  }
  
  public void setReviewContent(String reviewContent) {
    this.reviewContent = reviewContent;
  }
  
  public void setType(reviewType type) {
    this.type = type;
  }
  
  public void setRating(int rating) {
    this.rating = rating;
  }
   
  public enum reviewType {
    POSITIVE,
    NEGATIVE,
    NEUTRAL
  }
  
  @Override
  public String toString() {
    return "Review {reviewId=' " + reviewId + "'. reviewTitle=" + reviewTitle + ", reviewContent=" 
        + reviewContent + ", type=" + type + ", rating=" + rating + "}";
  }
}

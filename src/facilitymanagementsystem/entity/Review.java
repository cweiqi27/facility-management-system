package facilitymanagementsystem.entity;

import facilitymanagementsystem.adt.ListInterface;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
  private final LocalDateTime timestampCreate;
  private LocalDateTime timestampUpdate;
  private ListInterface<Facility> facilityList;
  private User user;
  
  public Review() {
    this.timestampCreate = LocalDateTime.now();
    this.timestampUpdate = LocalDateTime.now();
  }
  
  public Review(int reviewId, String reviewTitle, String reviewContent, int rating) {
    this.reviewId = reviewId;
    this.reviewTitle = reviewTitle;
    this.reviewContent = reviewContent;
    this.rating = rating;
    this.type = evaluateType();
    this.timestampCreate = LocalDateTime.now();
    this.timestampUpdate = LocalDateTime.now();
  }
  
  public int getReviewId() {
    return this.reviewId;
  }
  
  public String getReviewTitle() {
    return this.reviewTitle;
  }
  
  public String getReviewContent() {
    return this.reviewContent;
  }

  public int getRating() {
    return this.rating;
  }
  
  public reviewType getType() {
    return this.type;
  }
  
  public LocalDateTime getTimestampCreate() {
    return this.timestampCreate;
  }
  
  public LocalDateTime getTimestampUpdate() {
    return this.timestampUpdate;
  }
  
  public String getTimestampCreateFormatted() {
    return formatDate(timestampCreate);
  }
  
  public String getTimestampUpdateFormatted() {
    return formatDate(timestampUpdate);
  }
  
  public User getUser() {
    return this.user;
  }
  
  public ListInterface<Facility> getFacilityList() {
    return this.facilityList;
  }
  
  public void setReviewId(int reviewId) {
    this.reviewId = reviewId;
  }
  
  public void setReviewTitle(String reviewTitle) {
    this.reviewTitle = reviewTitle;
  }
  
  public void setReviewContent(String reviewContent) {
    this.reviewContent = reviewContent;
  }
  
  public void setRating(int rating) {
    this.rating = rating;
  }
  
  public void setType() {
    this.type = evaluateType();
  }
  
  public void setTimestampUpdate() {
    this.timestampUpdate = LocalDateTime.now();
  }
  
  public void setUser(User user) {
    this.user = user;
  }
  
  public void setFacilityList(ListInterface<Facility> facilityList) {
    this.facilityList = facilityList;
  }

  public enum reviewType {
    POSITIVE,
    NEGATIVE,
    NEUTRAL
  }
  
  private reviewType evaluateType() {
    if(this.rating > 3)
      return reviewType.POSITIVE;
    else if(this.rating == 3)
      return reviewType.NEUTRAL;
    else
      return reviewType.NEGATIVE;
  }
  
  private String formatDate(LocalDateTime timestamp) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yy, h:mma");
    return timestamp.format(formatter);
  }
  
  
  @Override
  public String toString() {
    return "Review {reviewId=" + reviewId + ", reviewTitle=" + reviewTitle + ", reviewContent=" 
        + reviewContent + ", rating=" + rating + ", type=" + type + ", timestampCreate=" + timestampCreate + "}";
  }
}

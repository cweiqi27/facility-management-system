
package facilitymanagementsystem.entity;


public class FacilityBook implements Comparable<FacilityBook>{
    private int Id;
    private String name, facility, duration;

    public FacilityBook(int Id, String name, String facility, String duration) {
        this.Id = Id;
        this.name = name;
        this.facility = facility;
        this.duration = duration;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    @Override
    public String toString(){
    	return Id + "\t" + name + "\t" + facility + "\t" + "\t" + duration + "\n";
    }
    
    /**
     *
     * @param c
     * @return
     */
    @Override
    public int compareTo(FacilityBook c){
        return (int)(this.Id-c.Id);
    }
}

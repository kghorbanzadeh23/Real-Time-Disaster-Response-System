package edu.ucalgary.oop;

public class Supply {
    private String type;
    private int quantity;
    private DisasterVictim supplyFor;
    private Location location;

    public Supply(String type, int quantity, Location location) {
        this.type = type;
        this.quantity = quantity;
        setLocation(location);
    }

    public String getType() { return this.type; }
    public void setType(String type) { this.type = type; }
    public int getQuantity() { return this.quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public DisasterVictim getSupplyFor() {return supplyFor;}
    public void setSupplyFor(DisasterVictim supplyFor) {
        this.supplyFor = supplyFor;
        if(location != null){
            location.removeSupply(this);
        }
    }
    
    public Location getLocation(){return this.location;}
    public void setLocation(Location location){
        
        if(this.location != null) {
            this.location.removeSupply(this); // Remove from old location
        }
        this.location = location;
        if(this.location != null) {
            this.location.addSupply(this); // Add to new location
        }
        
    }
    

}

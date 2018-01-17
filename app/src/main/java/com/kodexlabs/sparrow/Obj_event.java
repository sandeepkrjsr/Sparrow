package com.kodexlabs.sparrow;

public class Obj_event {
    private String Id, Name, Date, Venue, Desc, Image, Organiser, Website;

    Obj_event() {};   //defalut constructor is required for firebase you dumbfuck piece of shit;

    Obj_event(String Id, String Name, String Date, String Venue, String Desc, String Image, String Organiser, String Website){
        this.Id = Id;
        this.Name = Name;
        this.Date = Date;
        this.Venue = Venue;
        this.Desc = Desc;
        this.Image = Image;
        this.Organiser = Organiser;
        this.Website = Website;
    }

    //yha pe dedo hawasi sala


    public void setId(String id) {
        Id = id;
    }

    public void setOrganiser(String organiser) {
        Organiser = organiser;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setVenue(String venue) {
        Venue = venue;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    //yha se lelo bhikari


    public String getId() {
        return Id;
    }

    public String getDate() {
        return Date;
    }

    public String getDesc() {
        return Desc;
    }

    public String getImage() {
        return Image;
    }

    public String getName() {
        return Name;
    }

    public String getVenue() {
        return Venue;
    }

    public String getOrganiser() {
        return Organiser;
    }

    public String getWebsite() {
        return Website;
    }
}

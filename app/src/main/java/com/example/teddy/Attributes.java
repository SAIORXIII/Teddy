package com.example.teddy;


public class Attributes{
    private Long objectID;
    private String naam_label;
    private String Type;
    private String Prijstype;
    private String Straat;
    private String Huisnr;
    private String Busnr;
    private String Postcode;
    private String District;
    private String URL;
    private String Link;
    private String Email;
    private String Telefoon;
    private String Begindatum;
    private String Dienst_label;
    private String Capa_reeel;
    private Geometry geometry;

    private Double xCoordinate;
    private Double yCoordinate;

    public Attributes(Long objectID, String naam_label, String type,
                      String prijstype, String straat,
                      String huisnr, String busnr, String postcode,
                      String district, String URL, String link, String email,
                      String telefoon, String begindatum, String dienst_label,
                      String capa_reeel, Double xCoordinate, Double yCoordinate) {

        this.objectID = objectID;
        this.naam_label = naam_label;
        this.Type = type;
        this.Prijstype = prijstype;
        this.Straat = straat;
        this.Huisnr = huisnr;
        this.Busnr = busnr;
        this.Postcode = postcode;
        this.District = district;
        this.URL = URL;
        this.Link = link;
        this.Email = email;
        this.Telefoon = telefoon;
        this.Begindatum = begindatum;
        this.Dienst_label = dienst_label;
        this.Capa_reeel = capa_reeel;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;

    }

    public Attributes(){}

    public Attributes(String naam, String adres){
        this.naam_label = naam;
        this.District = adres;
    }


    public Double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Long getObjectID() {
        return objectID;
    }

    public void setObjectID(Long objectID) {
        this.objectID = objectID;
    }

    public String getNaam_label() {
        return naam_label;
    }

    public void setNaam_label(String naam_label) {
        this.naam_label = naam_label;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPrijstype() {
        return Prijstype;
    }

    public void setPrijstype(String prijstype) {
        Prijstype = prijstype;
    }

    public String getStraat() {
        return Straat;
    }

    public void setStraat(String straat) {
        Straat = straat;
    }

    public String getHuisnr() {
        return Huisnr;
    }

    public void setHuisnr(String huisnr) {
        Huisnr = huisnr;
    }

    public String getBusnr() {
        return Busnr;
    }

    public void setBusnr(String busnr) {
        Busnr = busnr;
    }

    public String getPostcode() {
        return Postcode;
    }

    public void setPostcode(String postcode) {
        Postcode = postcode;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelefoon() {
        return Telefoon;
    }

    public void setTelefoon(String telefoon) {
        Telefoon = telefoon;
    }

    public String getBegindatum() {
        return Begindatum;
    }

    public void setBegindatum(String begindatum) {
        Begindatum = begindatum;
    }

    public String getDienst_label() {
        return Dienst_label;
    }

    public void setDienst_label(String dienst_label) {
        Dienst_label = dienst_label;
    }

    public String getCapa_reeel() {
        return Capa_reeel;
    }

    public void setCapa_reeel(String capa_reeel) {
        Capa_reeel = capa_reeel;
    }






    @Override
    public String toString() {
        return "Attributes{" +
                "objectID=" + objectID +
                ", naam_label='" + naam_label + '\'' +
                ", URL='" + URL + '\'' +
                ", coordinate='" + getxCoordinate() + " " + getyCoordinate() + '\'' +
                '}';
    }


}

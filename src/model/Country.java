package model;

public class Country {
    private int countryId;
    private String countryName;
    private int countryMonthTotal;
    private String countryMonth;

    public Country(String countryMonth, int countryMonthTotal){
        this.countryMonth = countryMonth;
        this.countryMonthTotal = countryMonthTotal;
    }
    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }
public int getCountryMonthTotal(){
    return countryMonthTotal;
}

public String getCountryMonth(){
        return countryMonth;
}
    public Country(int countryId){
        this.countryId = countryId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return (countryName);
    }
}

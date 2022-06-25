package model;

public class Country {
    private int countryId;
    private String countryName;
    private int countryMonthTotal;
    private String countryMonth;

    /**
     * Overloaded constructor for Country that is used in CountryDAO to get associated appointment count per country
     *
     * @param countryMonth      country month
     * @param countryMonthTotal country month total
     */
    public Country(String countryMonth, int countryMonthTotal) {
        this.countryMonth = countryMonth;
        this.countryMonthTotal = countryMonthTotal;
    }

    /**
     * Overloaded constructor for Country used in CountryDAO that gets all countries to an observablelist
     *
     * @param countryId
     * @param countryName
     */
    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     * Getter for totals for country month
     * @return countryMonthTotal
     */
    public int getCountryMonthTotal() {
        return countryMonthTotal;
    }

    /**
     * Getter for country
     * @return
     */
    public String getCountryMonth() {
        return countryMonth;
    }


    /**
     * Getter for country Id
     *
     * @return countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Setter for country Id
     *
     * @param countryId country Id
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Getter for country name
     *
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Setter for country name
     *
     * @param countryName country name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * ToString method to display countryNames in combo boxes instead of memory addresses
     *
     * @return countryName
     */
    @Override
    public String toString() {
        return (countryName);
    }
}

package com.example.mywebsite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User class
 */
public class User {
    private String name, surname, workAddress, homeAddress, gender;
    private Date birthdate;

    /**
     * empty constructor
     */
    public User() {

    }

    /**
     * Constructor with parameters. sets work and home address to ""
     *
     * @param name      name of user String type
     * @param surname   surname of user String type
     * @param birthdate birthdate of user Date type
     * @param gender    gender of user String type
     */
    public User(String name, String surname, Date birthdate, String gender) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthdate = birthdate;
        workAddress = "";
        homeAddress = "";
    }

    /**
     * @return the name of the user. String
     */
    public String getName() {
        return name;
    }

    /**
     * @param name value to be given as the name of the user. String type
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname of the user. String type
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname value to be given as the surnname of the user. String type
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the workAddress of the user. String type
     */
    public String getWorkAddress() {
        return workAddress;
    }

    /**
     * @param workAddress value to be given as the workAddress of the user,if null it takes "" as value. String type
     */
    public void setWorkAddress(String workAddress) {
        if (workAddress==null){
            this.workAddress="";
        }else{
            this.workAddress = workAddress;
        }
    }

    /**
     * @return the homeAddress of the user. String type
     */
    public String getHomeAddress() {
        return homeAddress;
    }

    /**
     * @param homeAddress value to be given as the homeAddress of the user. if null it takes "" as value. String type
     */
    public void setHomeAddress(String homeAddress) {
        if (homeAddress==null){
            this.homeAddress="";
        }else{
            this.homeAddress = homeAddress;
        }
    }

    /**
     * @return the gender of the user. String type
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender value to be given as the gender of the user. String type
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the birthDate of the user. Date type
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * @param birthdate value to be given as the birthDate of the user. Date type
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Removes time from the date. Used in user.jsp
     * @return string in dd-MM-yy format.
     */
    public String showDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        String strDate = dateFormat.format(birthdate);
        return strDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                ", workAddress='" + workAddress + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}

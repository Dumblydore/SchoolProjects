/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.medwards11.person;

/**
 *
 * @author Maurice
 */
public class Person {
    private String name;
    private String eyeColor;
    private String hairColor;
    private String height;
    private String weight;
    
    public Person(String personName, String personEyeColor, String personHairColor, String personHeight, String personWeight) {
        name = personName;
        eyeColor = personEyeColor;
        hairColor = personHairColor;
        height = personHeight;
        weight = personWeight;
    }
    
    public String getName(){
        return name;
    }
    public String getEyeColor(){
        return eyeColor;
    }
    public String getHairColor(){
        return hairColor;
    }
    public String getHeight(){
        return height;
    }
    public String getWeight(){
        return weight;
    }
    
    public void setName(String personName){
        name = personName;
    }
    public void setEyeColor(String personEyeColor){
        eyeColor = personEyeColor;
    }
    public void setHairColor(String personHairColor){
        hairColor = personHairColor;
    }
    public void setHeight(String personHeight){
        height = personHeight;
    }
    public void setWeight(String personWeight){
        weight = personWeight;
    }

}

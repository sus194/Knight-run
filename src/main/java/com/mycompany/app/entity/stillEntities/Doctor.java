package com.mycompany.app.entity.stillEntities;

/**
 * The class for the doctor.
 * 
 * @author Sukhraj
 */
public class Doctor extends StillEntity{

    /**
     * The constructor for the Doctor class. It calls it's super class
     * StillEntity.
     * 
     * @param x The x position of the doctor
     * @param y The y position of the doctor
     * @param imgplace The name of the image file for the doctor
     */
    public Doctor(int x, int y, String imgplace) {
        super(x, y, imgplace);
    }

}

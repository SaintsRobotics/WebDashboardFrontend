package com.saintsrobotics.webinterfacebot.motors;


import java.lang.reflect.Field;
public abstract class MotorSet{
    private int[] motors;
    private boolean enabled = false;
    
    /**
     * Adds a lock to the motors specified in this class to @link Motors ,
     * preventing two @link MotorSet from attempting to access the same motors
     */
    public void enable() throws MotorLockedException{
        Field[] fields = motors.getClass().getDeclaredFields();
        int[] motorsTemp = new int[fields.length];
        for(int i = 0; i < fields.length; i++){
            try {
				motorsTemp[i] = ((Motor)(fields[i].get(this))).getPin();
			} catch (IllegalArgumentException | IllegalAccessException e) {
				System.out.println("ay");
			}
            if(Motors.locks[motorsTemp[i]]) throw new MotorLockedException();
        }
        for(int i : motorsTemp){
            Motors.lock(i);
        }
        this.motors = motorsTemp;
        this.enabled = true;
    }
    /* Unlock the motors associated with this MotorSet */
    public void disable(){
        for(int i : motors){
            Motors.unlock(i);
            this.enabled = false;
        }
    }
}
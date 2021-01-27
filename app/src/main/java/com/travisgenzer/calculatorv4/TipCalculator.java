package com.travisgenzer.calculatorv4;

public class TipCalculator {
    private float tip;
    private float bill;
    private float group;
    private float temp;
    public TipCalculator(float newTip, float newBill, float newGroup) {
        setTip(newTip);
        setBill(newBill);
        setGroup(newGroup);
    }

    public float getTip() {
        return tip;
    }

    public float getBill()
    {
        return bill;
    }

    public float getGroup() {
        return group;
    }

    public void setTip(float newTip)
    {
        if(newTip > 0)
            tip = newTip;
    }

    public void setBill(float newBill)
    {
        if(newBill > 0)
            bill = newBill;
    }

    public void setGroup(float newGroup) {
        if(newGroup > 0)
            group = newGroup;
    }

    public float tipAmount()
    {
        return bill * tip;
    }

    public float totalAmount()
    {
        temp = bill + tipAmount();
        if(group>1)return temp/group;
        else return temp;
    }

}

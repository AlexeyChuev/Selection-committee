package net.chuiev.selcommittee.entity;

import java.io.Serializable;

/**
 * Created by Алексей on 3/5/2016.
 */
public class Faculty  implements Serializable {
    private static final long serialVersionUID = 1099698953477481899L;

    private int id;
    private String name;
    private int budgetVolume;
    private int totalVolume;

    public Faculty(String name, int budgetVolume, int totalVolume) {

        this.name = name;
        this.budgetVolume = budgetVolume;
        this.totalVolume = totalVolume;
    }

    public Faculty(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBudgetVolume() {
        return budgetVolume;
    }

    public void setBudgetVolume(int budgetVolume) {
        this.budgetVolume = budgetVolume;
    }

    public int getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(int totalVolume) {
        this.totalVolume = totalVolume;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", budgetVolume=" + budgetVolume +
                ", totalVolume=" + totalVolume +
                '}';
    }
}

package com.github.goustkor.harvestplus.Utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;

import static org.bukkit.Material.*;

public class CheckCrop {

    private Block block;
    private Material material;
    private BlockData bdata;
    private Ageable age;
    private boolean isCrop = false;

    public CheckCrop(Block block) {
        this.block = block;
        this.material = this.block.getType();
    }

    public boolean isCrop(){
        if(block == null) return false;
        if(isCrop) return true;

        switch(this.material){
            case CARROTS:
            case WHEAT:
            case BEETROOTS:
            case POTATOES:
            case COCOA:
            case MELON_STEM:
            case PUMPKIN_STEM:
                isCrop = true;
                return true;
        }

        return false;
    }

    public int getAge(){
        if(isCrop()){
            if(age == null) generateBlockData();

            return age.getAge();
        }
        return -1;
    }

    public int getMaxAge(){
        if(isCrop()){
            if(age == null) generateBlockData();

            return age.getMaximumAge();
        }

        return -1;
    }

    public Material getType(){
        return this.material;
    }

    public Material getSeed(){
        if(isCrop()){
            switch(this.material){
                case CARROTS:
                    return CARROT;
                case WHEAT:
                    return WHEAT_SEEDS;
                case BEETROOTS:
                    return BEETROOT_SEEDS;
                case POTATOES:
                    return POTATO;
                case COCOA:
                    return COCOA_BEANS;
                case MELON_STEM:
                    return MELON_SEEDS;
                case PUMPKIN_STEM:
                    return PUMPKIN_SEEDS;
            }
        }

        return null;
    }

    public String getName(){
        String name = this.material.name().toLowerCase();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        name = name.split("_")[0];

        return name;
    }

    public int getPercent(){
        return (getAge() * 100) / getMaxAge();
    }

    public BlockData getBlockData(){
        if(bdata == null) generateBlockData();

        return bdata;
    }

    private void generateBlockData(){
        if(bdata == null) {
            bdata = block.getBlockData();
        }

        if(age == null){
            age = (Ageable) bdata;
        }

    }

}

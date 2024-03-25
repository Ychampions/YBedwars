package fr.ychampions.teams;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum TeamColor {

    RED,
    BLUE,
    YELLOW,
    GREEN,
    AQUA,
    WHITE,
    PINK,
    GRAY;

    public String formattedName(){
        String caps = this.toString();
        return String.valueOf(caps.charAt(0)).toUpperCase() + caps.substring(1, caps.length() - 1).toLowerCase();
    }

    public ChatColor getChatColor(){
        return null;
    }

    public Material woolMaterial() {

        Material teamWoolMaterial = Material.BLACK_WOOL;

        switch (this){
            case RED:
                teamWoolMaterial = Material.RED_WOOL;
                break;
            case AQUA:
                teamWoolMaterial = Material.CYAN_WOOL;
                break;
            case BLUE:
                teamWoolMaterial = Material.BLUE_WOOL;
                break;
            case GRAY:
                teamWoolMaterial = Material.GRAY_WOOL;
                break;
            case PINK:
                teamWoolMaterial = Material.PINK_WOOL;
                break;
            case GREEN:
                teamWoolMaterial = Material.LIME_WOOL;
                break;
            case WHITE:
                teamWoolMaterial = Material.WHITE_WOOL;
                break;
            case YELLOW:
                teamWoolMaterial = Material.YELLOW_WOOL;
        }
        return teamWoolMaterial;
    }

}

package fr.ychampions.teams;

import fr.ychampions.gamemanager.GameManager;
import fr.ychampions.setup.SetupWizardManager;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

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
        return String.valueOf(caps.charAt(0)).toUpperCase() + caps.substring(1).toLowerCase();
    }

    public ChatColor getChatColor(){
        if (this == PINK){
            return ChatColor.LIGHT_PURPLE;
        }
        return ChatColor.valueOf(this.toString());
    }

    public ItemStack woolMaterial() {

        ItemStack teamWoolMaterial = new ItemStack(Material.WOOL, 1);
        ItemMeta meta = teamWoolMaterial.getItemMeta();

        switch (this){
            case RED:
                teamWoolMaterial = new Wool(DyeColor.RED).toItemStack(1);
                meta.setDisplayName(ChatColor.RED + "Red");
                teamWoolMaterial.setItemMeta(meta);
                break;
            case AQUA:
                teamWoolMaterial = new Wool(DyeColor.CYAN).toItemStack(1);
                meta.setDisplayName(ChatColor.AQUA + "Aqua");
                teamWoolMaterial.setItemMeta(meta);
                break;
            case BLUE:
                teamWoolMaterial = new Wool(DyeColor.BLUE).toItemStack(1);
                meta.setDisplayName(ChatColor.BLUE + "Blue");
                teamWoolMaterial.setItemMeta(meta);
                break;
            case GRAY:
                teamWoolMaterial = new Wool(DyeColor.GRAY).toItemStack(1);
                meta.setDisplayName(ChatColor.GRAY + "Gray");
                teamWoolMaterial.setItemMeta(meta);
                break;
            case PINK:
                teamWoolMaterial = new Wool(DyeColor.PINK).toItemStack(1);
                meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Pink");
                teamWoolMaterial.setItemMeta(meta);
                break;
            case GREEN:
                teamWoolMaterial = new Wool(DyeColor.LIME).toItemStack(1);
                meta.setDisplayName(ChatColor.GREEN + "Green");
                teamWoolMaterial.setItemMeta(meta);
                break;
            case WHITE:
                teamWoolMaterial = new Wool(DyeColor.WHITE).toItemStack(1);
                meta.setDisplayName(ChatColor.WHITE + "White");
                teamWoolMaterial.setItemMeta(meta);
                break;
            case YELLOW:
                teamWoolMaterial = new Wool(DyeColor.YELLOW).toItemStack(1);
                meta.setDisplayName(ChatColor.YELLOW + "Yellow");
                teamWoolMaterial.setItemMeta(meta);
                break;
        }
        return teamWoolMaterial;
    }

}

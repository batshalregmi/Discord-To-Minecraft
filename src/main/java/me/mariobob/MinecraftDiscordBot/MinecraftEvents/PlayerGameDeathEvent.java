package me.mariobob.MinecraftDiscordBot.MinecraftEvents;

import lombok.RequiredArgsConstructor;
import me.mariobob.MinecraftDiscordBot.MinecraftDiscordBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.*;
import java.util.Objects;

@RequiredArgsConstructor
public class PlayerGameDeathEvent implements Listener {
    private final MinecraftDiscordBot plugin;

    @EventHandler
    public void onPlayerDeath(org.bukkit.event.entity.PlayerDeathEvent event){
        TextChannel textChannel = this.plugin.returnOrCreate(this.plugin.getMinecraftDiscordChannelName());
        if (textChannel != null) {
            EmbedBuilder em = new EmbedBuilder();
            em.setTitle(event.getEntity().getDisplayName() + " died!");
            em.setDescription(event.getDeathMessage());
            em.setColor(Color.red);
            em.setFooter("Died at: " + plugin.timestamp(), "https://crafatar.com/avatars/" + Objects.requireNonNull(event.getEntity().getPlayer()).getUniqueId());
            textChannel.sendMessage(em.build()).queue();
        } else{
            plugin.getServer().getLogger().severe("Minecraft channel not found!! Make sure it exists and is in the config.yml file.");
        }

    }

}

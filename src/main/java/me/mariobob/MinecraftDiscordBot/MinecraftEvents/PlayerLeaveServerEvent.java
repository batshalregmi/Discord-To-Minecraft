package me.mariobob.MinecraftDiscordBot.MinecraftEvents;

import lombok.RequiredArgsConstructor;
import me.mariobob.MinecraftDiscordBot.MinecraftDiscordBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;
import java.util.Objects;

@RequiredArgsConstructor
public class PlayerLeaveServerEvent implements Listener {
    private final MinecraftDiscordBot plugin;

    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent event){
        TextChannel textChannel = this.plugin.returnOrCreate(this.plugin.getMinecraftDiscordChannelName());
        EmbedBuilder em = new EmbedBuilder();
        if (textChannel != null) {
            if(event.getPlayer().isBanned()){
                em.setColor(Color.RED);
                em.setTitle("The Ban Hammer Has Spoken!");
                em.setDescription(event.getPlayer().getDisplayName() + " has been banned!" );
                em.setFooter(plugin.timestamp(), "https://crafatar.com/avatars/" + Objects.requireNonNull(event.getPlayer()).getUniqueId());
                textChannel.sendMessage(em.build()).queue();
            } else {
                em.setTitle(event.getPlayer().getDisplayName() + " has left the server!");
                em.setColor(Color.RED);
                em.setFooter("Joined at " + plugin.timestamp(), "https://crafatar.com/avatars/" + event.getPlayer().getUniqueId());
                textChannel.sendMessage(em.build()).queue();
            }
        }else{
            plugin.getServer().getLogger().severe("Minecraft channel not found!! Make sure it exists and is in the config.yml file.");
        }
    }
}

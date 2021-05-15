package me.mariobob.MinecraftDiscordBot.MinecraftEvents;

import lombok.RequiredArgsConstructor;
import me.mariobob.MinecraftDiscordBot.MinecraftDiscordBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
public class PlayerLeaveServerEvent implements Listener {
    private final MinecraftDiscordBot plugin;

    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent event){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        TextChannel textChannel = this.plugin.returnOrCreate(this.plugin.getMinecraftDiscordChannelName());
        if (textChannel != null) {
            EmbedBuilder em = new EmbedBuilder();
            em.setTitle(event.getPlayer().getDisplayName() + " has left the server!");
            em.setColor(Color.RED);
            em.setFooter("Joined at " + formatter.format(date), "https://crafatar.com/avatars/" + event.getPlayer().getUniqueId());
            textChannel.sendMessage(em.build()).queue();
        }
    }
}

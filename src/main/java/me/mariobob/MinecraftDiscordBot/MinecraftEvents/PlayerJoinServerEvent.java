package me.mariobob.MinecraftDiscordBot.MinecraftEvents;

import lombok.RequiredArgsConstructor;
import me.mariobob.MinecraftDiscordBot.MinecraftDiscordBot;
import me.mariobob.MinecraftDiscordBot.Util.Util;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;

@RequiredArgsConstructor
public class PlayerJoinServerEvent implements Listener {
    private final MinecraftDiscordBot plugin;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        TextChannel textChannel = this.plugin.returnOrCreate(this.plugin.getMinecraftDiscordChannelName());
        if (textChannel != null) {
            textChannel.sendMessage(Util.createEmbed(event.getPlayer().getDisplayName() + " has joined the server!", null, Color.GREEN, "Joined at " + plugin.timestamp(), null)).queue();
        } else {
            plugin.getServer().getLogger().severe("Minecraft channel not found!! Make sure it exists and is in the config.yml file.");
        }
    }
}

package me.mariobob.MinecraftDiscordBot.MinecraftEvents;

import lombok.RequiredArgsConstructor;
import me.mariobob.MinecraftDiscordBot.MinecraftDiscordBot;
import me.mariobob.MinecraftDiscordBot.Util.Util;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;

@RequiredArgsConstructor
public class PlayerLeaveServerEvent implements Listener {
    private final MinecraftDiscordBot plugin;

    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent event) {
        TextChannel textChannel = this.plugin.returnOrCreate(this.plugin.getMinecraftDiscordChannelName());
        if (textChannel != null) {
            if (event.getPlayer().isBanned()) {
                textChannel.sendMessage(Util.createEmbed("The Ban Hammer Has Spoken!", event.getPlayer().getDisplayName() + " has been banned!", Color.RED, MinecraftDiscordBot.getPlugin().timestamp(), null, null)).queue();
            } else {
                textChannel.sendMessage(Util.createEmbed(event.getPlayer().getDisplayName() + " has left the server!", null, Color.RED, MinecraftDiscordBot.getPlugin().timestamp(), null, null)).queue();
            }
        } else {
            plugin.getServer().getLogger().severe("Minecraft channel not found!! Make sure it exists and is in the config.yml file.");
        }
    }
}

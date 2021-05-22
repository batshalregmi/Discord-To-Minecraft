package me.mariobob.MinecraftDiscordBot.MinecraftEvents;

import lombok.RequiredArgsConstructor;
import me.mariobob.MinecraftDiscordBot.MinecraftDiscordBot;
import me.mariobob.MinecraftDiscordBot.Util.Util;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.*;

@RequiredArgsConstructor
public class PlayerGameDeathEvent implements Listener {
    private final MinecraftDiscordBot plugin;

    @EventHandler
    public void onPlayerDeath(org.bukkit.event.entity.PlayerDeathEvent event) {
        TextChannel textChannel = this.plugin.returnOrCreate(this.plugin.getMinecraftDiscordChannelName());
        if (textChannel != null) {
            textChannel.sendMessage(Util.createEmbed(event.getEntity().getDisplayName() + " died!", event.getDeathMessage(), Color.RED, "Died at: " + plugin.timestamp(), null)).queue();
        } else {
            plugin.getServer().getLogger().severe("Minecraft channel not found!! Make sure it exists and is in the config.yml file.");
        }

    }

}

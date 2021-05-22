package me.mariobob.MinecraftDiscordBot.MinecraftEvents;

import lombok.RequiredArgsConstructor;
import me.mariobob.MinecraftDiscordBot.MinecraftDiscordBot;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;


@RequiredArgsConstructor
public class PlayerAchievementGetEvent implements Listener {
    private final MinecraftDiscordBot plugin;

    @EventHandler
    public void onPlayerAchievementGet(PlayerAdvancementDoneEvent event) {
        TextChannel textChannel = this.plugin.returnOrCreate(this.plugin.getMinecraftDiscordChannelName());
        if (textChannel != null) {
            textChannel.sendMessage(event.getPlayer().getDisplayName() + " has gotten the achievement " + event.getAdvancement().getKey()).queue(); // TODO: fix this with NMS code?? idk
            textChannel.sendMessage("This advancement requires: " + event.getAdvancement().getCriteria()).queue();
        } else {
            plugin.getServer().getLogger().severe("Minecraft channel not found!! Make sure it exists and is in the config.yml file.");
        }

    }
}

package me.mariobob.MinecraftDiscordBot.MinecraftEvents;

import lombok.RequiredArgsConstructor;
import me.mariobob.MinecraftDiscordBot.MinecraftDiscordBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@RequiredArgsConstructor
public class PlayerGameDeathEvent implements Listener {
    private final MinecraftDiscordBot plugin;

    @EventHandler
    public void onPlayerDeath(org.bukkit.event.entity.PlayerDeathEvent event){
        TextChannel textChannel = this.plugin.returnOrCreate(this.plugin.getMinecraftDiscordChannelName());
        if (textChannel != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date = new Date();
            EmbedBuilder em = new EmbedBuilder();
            em.setTitle(event.getEntity().getDisplayName() + " died!");
            em.setDescription(event.getDeathMessage());
            em.setColor(Color.red);
            em.setFooter("Died at: " + formatter.format(date), "https://crafatar.com/avatars/" + Objects.requireNonNull(event.getEntity().getPlayer()).getUniqueId());
            textChannel.sendMessage(em.build()).queue();
        }

    }

}

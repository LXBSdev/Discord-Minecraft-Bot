package main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.User.Profile;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.ObjectMapper;

public class commands extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@Nonnull SlashCommandInteractionEvent event) {
        String command = event.getName();

        if (command.equals("profile")) {
            ObjectMapper mapper = new ObjectMapper();
            Map<Integer, Profile> map = new HashMap<Integer, Profile>();
            EmbedBuilder emb = new EmbedBuilder();
            String user = event.getOption("minecraft-name").toString();
            try {
                map = mapper.readValue(new File("http://minecraft.data.lxbs.online/profile/profile.json"),
                        new TypeReference<Map<Integer, Profile>>() {
                        });
                for (Profile value : map.values()) {
                    if (value.getUserNameString() == user) {
                        emb.setTitle(user)
                                .setImage(user)
                                .setColor(0x4d8a5d)
                                .addField("Network Level", value.getLevel(), true)
                                .addField("Status", value.getOnlineStatus(), true);
                    } else {
                        event.reply("There is no player with that name").queue();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

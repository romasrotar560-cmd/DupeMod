package com.example.dupe;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("dupemod")
public class DupeMod {
    public DupeMod() { MinecraftForge.EVENT_BUS.register(this); }
    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("dupe").executes(context -> {
            ServerPlayerEntity player = context.getSource().asPlayer();
            ItemStack item = player.getOffhandItem();
            if (item.isEmpty()) {
                player.sendMessage(new StringTextComponent("§cПоложи вещь в левую руку!"), player.getUUID());
                return 0;
            }
            ItemStack clone = item.copy();
            if (!player.inventory.add(clone)) player.drop(clone, false);
            player.sendMessage(new StringTextComponent("§aРазмножено!"), player.getUUID());
            return 1;
        }));
    }
}

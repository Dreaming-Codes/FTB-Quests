package com.feed_the_beast.ftbquests.quest.task;

import com.feed_the_beast.ftblib.lib.config.ConfigGroup;
import com.feed_the_beast.ftblib.lib.io.DataIn;
import com.feed_the_beast.ftblib.lib.io.DataOut;
import com.feed_the_beast.ftbquests.quest.Quest;
import com.feed_the_beast.ftbquests.quest.QuestData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Date;
import java.util.Objects;

/**
 * @author DreamingCodes
 */

public class BiomeTask extends Task {
    public String biome = "";
    public BiomeTask(Quest quest) { super(quest); }


    @Override
    public TaskType getType() {
        return FTBQuestsTasks.BIOME;
    }

    @Override
    public void writeData(NBTTagCompound nbt)
    {
        super.writeData(nbt);
        nbt.setString("biome", biome);
    }

    @Override
    public void readData(NBTTagCompound nbt)
    {
        super.readData(nbt);
        biome = nbt.getString("biome");
    }

    @Override
    public void writeNetData(DataOut data)
    {
        super.writeNetData(data);
        data.writeString(biome);
    }

    @Override
    public void readNetData(DataIn data)
    {
        super.readNetData(data);
        biome = data.readString();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getConfig(ConfigGroup config)
    {
        super.getConfig(config);

        config.addString("biome", () -> biome, v -> biome = v, "").setDisplayName(new TextComponentTranslation("ftbquests.task.ftbquests.biome"));
    }

    @Override
    public String getAltTitle()
    {
        return I18n.format("ftbquests.task.ftbquests.biome.text", (biome));
    }
    @Override
    public int autoSubmitOnPlayerTick()
    {
        return(20);
    }

    public TaskData createData(QuestData data)
    {
        return new BiomeTask.Data(this, data);
    }
    public static class Data extends BooleanTaskData<BiomeTask>
    {
        private static boolean ciao;

        private Data(BiomeTask task, QuestData data)
        {
            super(task, data);
        }
        public static void setCiao(){
            ciao = false;
            System.out.println("Unloaded");
        }
        @Override
        public boolean canSubmit(EntityPlayerMP player){
            while (ciao = false) {
                long start = new Date().getTime();
                while (new Date().getTime() - start < 1000L) {}
                ciao = true;
            }
            Minecraft mc = Minecraft.getMinecraft();
            String biome = Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(mc.world.getBiome(new BlockPos(Objects.requireNonNull(mc.getRenderViewEntity()).posX, mc.getRenderViewEntity().getEntityBoundingBox().minY, mc.getRenderViewEntity().posZ)))).toString();
            return task.biome.toString().contains(biome);
        }
    }
}

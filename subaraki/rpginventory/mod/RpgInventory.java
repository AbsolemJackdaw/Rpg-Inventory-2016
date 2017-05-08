package subaraki.rpginventory.mod;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryCapability;
import subaraki.rpginventory.handler.DeathAndAttachEvents;
import subaraki.rpginventory.handler.GuiHandler;
import subaraki.rpginventory.handler.JeweleryEffectsHandler;
import subaraki.rpginventory.handler.PlayerTracker;
import subaraki.rpginventory.handler.client.KeyHandler;
import subaraki.rpginventory.handler.proxy.ServerProxy;
import subaraki.rpginventory.item.RpgItems;
import subaraki.rpginventory.network.PacketHandler;

@Mod(modid = RpgInventory.MODID, name = RpgInventory.NAME, version = RpgInventory.VERSION, dependencies="required-after:subcommonlib")
public class RpgInventory {

	public static final String MODID = "rpginventory";
	public static final String NAME = "Rpg Inventory";
	public static final String VERSION = "1.11.2 5.1.0.0";

	@SidedProxy(clientSide = 
			"subaraki.rpginventory.handler.proxy.ClientProxy",
			serverSide = 
			"subaraki.rpginventory.handler.proxy.ServerProxy")
	public static ServerProxy proxy;

	public static RpgInventory INSTANCE;
	
	public static Logger log = LogManager.getLogger(MODID);
	
	@EventHandler 
	public void preInit(FMLPreInitializationEvent event) {

		INSTANCE = this;

		ModMetadata modMeta = event.getModMetadata();
		modMeta.authorList = Arrays.asList(new String[] { "Subaraki" });
		modMeta.autogenerated = false;
		modMeta.credits = "ZetaHunter and Richard Digits (retired team members), Resinesin and Take Weiland for ultra-testing, hints and tips";
		modMeta.description = "Expanded Inventory, Class Armor, and Ultra Fine 3D weapons !";
		modMeta.url = "https://github.com/ArtixAllMighty/Rpg-Inventory-2016/wiki";

		proxy.registerKey();
		
		//inititate networking
		new PacketHandler();

		//always register items in preInit
		RpgItems.init();
		
		proxy.registerRenders();
		proxy.registerClientEvents();

		//register gui handler
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

		new RpgInventoryCapability().register();

		//queue subscribed events
		new DeathAndAttachEvents();
		new KeyHandler();
		new JeweleryEffectsHandler();
		new PlayerTracker();
	}

	@EventHandler
	public void init(FMLInitializationEvent event){
		//register colors after preInit
		proxy.registerColors();
		proxy.addRenderLayers();
	}
}

package subaraki.rpginventory.utility;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 *
 * @author Richard Smith <rich1051414@gmail.com>
 */
public class Targetting {


	/**@params parDistance : distance of blocks to check
	 * return the entity you are looking at. null if no entity is looked at within parDistance blocks
	 * */
	@SideOnly(Side.CLIENT)
	public static EntityLivingBase isTargetingLivingEntity(double parDistance) {
		
		net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getMinecraft();
		
		if(! (mc.getRenderViewEntity() instanceof EntityLivingBase))
			return null;

		EntityLivingBase viewEntity = (EntityLivingBase)mc.getRenderViewEntity();
		World worldObj = viewEntity.worldObj;
		Entity Return = null;

		if (viewEntity != null) {

			RayTraceResult objectMouseOver = viewEntity.rayTrace(parDistance, 0.5F);
			Vec3d playerPosition = viewEntity.getPositionEyes(1.0F);

			double farDist = parDistance;
			if (objectMouseOver != null) {
				farDist = objectMouseOver.hitVec.distanceTo(playerPosition);
			}
			double closest = farDist;

			Vec3d dirVec = viewEntity.getLookVec();
			Vec3d lookFarCoord = playerPosition.addVector(dirVec.xCoord
					* parDistance, dirVec.yCoord * parDistance, dirVec.zCoord
					* parDistance);

			List<EntityLivingBase> targettedEntities = worldObj.getEntitiesWithinAABB(
					EntityLivingBase.class,
					viewEntity.getEntityBoundingBox().
					addCoord(dirVec.xCoord * parDistance,
							dirVec.yCoord * parDistance,
							dirVec.zCoord * parDistance).expand(0.1,0.1, 0.1));

			targettedEntities.remove(viewEntity);

			for (EntityLivingBase targettedEntity : targettedEntities) {
				if (targettedEntity != null) {
					double precheck = viewEntity.getDistanceToEntity(targettedEntity);
					
					RayTraceResult mopElIntercept = targettedEntity.getEntityBoundingBox().
							calculateIntercept(playerPosition, lookFarCoord);
					
					if (mopElIntercept != null) {
						if (precheck < closest) {
							Return = targettedEntity;
							closest = precheck;
						}
					}
				}
			}
		}
		if ((Return != null) && (Return instanceof EntityLivingBase)) {
			return (EntityLivingBase) Return;
		}
		return null;
	}
}
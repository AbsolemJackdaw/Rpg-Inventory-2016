package subaraki.rpginventory.render.models;

import java.util.ArrayList;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNecklace extends ModelBase {
	// fields
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;

	public ArrayList<ModelRenderer> parts = new ArrayList<ModelRenderer>();

	public ModelNecklace() {
		textureWidth = 64;
		textureHeight = 32;

		Shape4 = new ModelRenderer(this, 24, 0);
		Shape4.addBox(1.5F, 2F, -2.7F, 1, 1, 1);
		Shape4.setRotationPoint(0F, 0F, 0F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 8, 0);
		Shape5.addBox(-2.5F, 2F, -2.7F, 1, 1, 1);
		Shape5.setRotationPoint(0F, 0F, 0F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape1 = new ModelRenderer(this, 0, 4);
		Shape1.addBox(-1F, 3F, -3F, 2, 2, 1);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 20, 0);
		Shape2.addBox(1F, 2.5F, -2.8F, 1, 1, 1);
		Shape2.setRotationPoint(0F, 0F, 0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 4, 0);
		Shape3.addBox(-2F, 2.5F, -2.8F, 1, 1, 1);
		Shape3.setRotationPoint(0F, 0F, 0F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 12, 0);
		Shape6.addBox(-3F, 1F, -2.6F, 1, 1, 1);
		Shape6.setRotationPoint(0F, 0F, 0F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 28, 0);
		Shape7.addBox(2F, 1F, -2.6F, 1, 1, 1);
		Shape7.setRotationPoint(0F, 0F, 0F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 32, 0);
		Shape8.addBox(2.5F, 0F, -2.5F, 1, 1, 1);
		Shape8.setRotationPoint(0F, 0F, 0F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 16, 0);
		Shape9.addBox(-3.5F, 0F, -2.5F, 1, 1, 1);
		Shape9.setRotationPoint(0F, 0F, 0F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);

		parts.add(Shape1);
		parts.add(Shape2);
		parts.add(Shape3);
		parts.add(Shape4);
		parts.add(Shape5);
		parts.add(Shape6);
		parts.add(Shape7);
		parts.add(Shape8);
		parts.add(Shape9);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
	}

	public void renderNecklace(float f5) {
		Shape4.render(f5);
		Shape5.render(f5);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity ent) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
	}
}
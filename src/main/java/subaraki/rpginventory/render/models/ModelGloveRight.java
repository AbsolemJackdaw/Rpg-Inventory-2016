package subaraki.rpginventory.render.models;

import java.util.ArrayList;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGloveRight extends ModelBase {
	// fields
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;

	public ArrayList<ModelRenderer> parts = new ArrayList<ModelRenderer>();

	public ModelGloveRight() {
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 9);
		Shape1.addBox(-3F, 8F, -3F, 4, 1, 1);
		Shape1.setRotationPoint(-5F, 2F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 13);
		Shape2.addBox(-4.4F, 8F, -2F, 1, 1, 4);
		Shape2.setRotationPoint(-5F, 2F, 0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 9);
		Shape3.addBox(-3F, 8F, 2F, 4, 1, 1);
		Shape3.setRotationPoint(-5F, 2F, 0F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 34, 10);
		Shape4.addBox(-3.9F, 6.5F, -2F, 1, 3, 4);
		Shape4.setRotationPoint(-5F, 2F, 0F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 21, 7);
		Shape5.addBox(-3F, 6.5F, -2.6F, 4, 3, 1);
		Shape5.setRotationPoint(-5F, 2F, 0F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 21, 7);
		Shape6.addBox(-3F, 6.5F, 1.6F, 4, 3, 1);
		Shape6.setRotationPoint(-5F, 2F, 0F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 21, 0);
		Shape7.addBox(-3F, 5F, -2.4F, 4, 5, 1);
		Shape7.setRotationPoint(-5F, 2F, 0F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 34, 0);
		Shape8.addBox(-3.6F, 5F, -2F, 1, 5, 4);
		Shape8.setRotationPoint(-5F, 2F, 0F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 21, 0);
		Shape9.addBox(-3F, 5F, 1.4F, 4, 5, 1);
		Shape9.setRotationPoint(-5F, 2F, 0F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(this, 0, 0);
		Shape10.addBox(-3F, 9.5F, -2F, 4, 1, 4);
		Shape10.setRotationPoint(-5F, 2F, 0F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 34, 0);
		Shape11.addBox(0.1F, 5F, -2F, 1, 5, 4);
		Shape11.setRotationPoint(-5F, 2F, 0F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 21, 0);
		Shape12.addBox(-3.3F, 5.3F, -2.3F, 1, 4, 1);
		Shape12.setRotationPoint(-5F, 2F, 0F);
		Shape12.setTextureSize(64, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(this, 21, 0);
		Shape13.addBox(-3.3F, 5.3F, 1.3F, 1, 4, 1);
		Shape13.setRotationPoint(-5F, 2F, 0F);
		Shape13.setTextureSize(64, 32);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);

		parts.add(Shape1);
		parts.add(Shape2);
		parts.add(Shape3);
		parts.add(Shape4);
		parts.add(Shape5);
		parts.add(Shape6);
		parts.add(Shape7);
		parts.add(Shape8);
		parts.add(Shape9);
		parts.add(Shape10);
		parts.add(Shape11);
		parts.add(Shape12);
		parts.add(Shape13);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		for(ModelRenderer model : parts)
			model.render(f5);
	}

	public void renderRightGlove(float f5) {
		for(ModelRenderer model : parts)
			model.render(f5);
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

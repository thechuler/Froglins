package net.rbk.froglins.Entidades.Modelos;// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.util.Mth;
import net.rbk.froglins.Entidades.Animaciones.FroglinAnimaciones;
import net.rbk.froglins.Entidades.Animaciones.FrogronkAnimaciones;
import net.rbk.froglins.Entidades.Entity.AbstractFroglin;
import net.rbk.froglins.Entidades.Entity.Frogronk;

public class FrogronkModel<T extends Frogronk> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "frogronk"), "main");
	private final ModelPart root;
	private final ModelPart BrazoDer;
	private final ModelPart BrazoIzq;
	private final ModelPart Cabeza;
	private final ModelPart MandibulaSup;
	private final ModelPart MandibulaInf;
	private final ModelPart Cuerpo;

	public FrogronkModel(ModelPart root) {
		this.root = root.getChild("root");
		this.BrazoDer = this.root.getChild("BrazoDer");
		this.BrazoIzq = this.root.getChild("BrazoIzq");
		this.Cabeza = this.root.getChild("Cabeza");
		this.MandibulaSup = this.Cabeza.getChild("MandibulaSup");
		this.MandibulaInf = this.Cabeza.getChild("MandibulaInf");
		this.Cuerpo = this.root.getChild("Cuerpo");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -3.0F));

		PartDefinition BrazoDer = root.addOrReplaceChild("BrazoDer", CubeListBuilder.create().texOffs(94, 0).addBox(0.0F, 0.0F, -8.0F, 9.0F, 29.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(13.0F, -29.0F, 9.0F));

		PartDefinition BrazoIzq = root.addOrReplaceChild("BrazoIzq", CubeListBuilder.create().texOffs(0, 111).addBox(-9.0F, 0.0F, -8.0F, 9.0F, 29.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(-13.0F, -29.0F, 9.0F));

		PartDefinition Cabeza = root.addOrReplaceChild("Cabeza", CubeListBuilder.create(), PartPose.offset(0.0F, -21.0F, 2.0F));

		PartDefinition MandibulaSup = Cabeza.addOrReplaceChild("MandibulaSup", CubeListBuilder.create().texOffs(0, 87).addBox(-10.0F, -8.0F, -16.0F, 20.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition MandibulaInf = Cabeza.addOrReplaceChild("MandibulaInf", CubeListBuilder.create().texOffs(0, 50).addBox(-12.0F, 1.0F, -20.0F, 24.0F, 17.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition cube_r1 = MandibulaInf.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(88, 66).addBox(7.0F, -14.0F, -17.0F, 4.0F, 13.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(88, 50).addBox(-11.0F, -14.0F, -17.0F, 4.0F, 13.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -0.8F, 0.3491F, 0.0F, 0.0F));

		PartDefinition Cuerpo = root.addOrReplaceChild("Cuerpo", CubeListBuilder.create().texOffs(0, 0).addBox(-13.0F, -33.0F, -6.0F, 26.0F, 29.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(72, 87).addBox(-13.0F, -30.0F, 15.0F, 26.0F, 26.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 5.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}


	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(T t, float v, float v1, float v2, float v3, float v4) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
	    this.applyHeadRotation(v3,v4);
		this.animateWalk(FrogronkAnimaciones.WALK, v, v1, 2f, 2.5f);
		this.animate(((AbstractFroglin) t).deathAnimationState, FrogronkAnimaciones.DEATH, v2, 1f);
		this.animate(((AbstractFroglin) t).idleAnimationState, FrogronkAnimaciones.IDLE, v2, 1f);
		this.animate(((AbstractFroglin) t).gruñirAnimationState, FrogronkAnimaciones.RUGIDO2, v2, 1f);
		this.animate(((AbstractFroglin) t).attackAnimationState, FrogronkAnimaciones.ATTACK, v2, 1f);
	}



	private void applyHeadRotation(float headYaw, float headPitch) {
		headYaw = Mth.clamp(headYaw, -30f, 30f);
		headPitch = Mth.clamp(headPitch, -25f, 45);

		this.Cabeza.yRot = headYaw * ((float)Math.PI / 180f);
		this.Cabeza.xRot = headPitch *  ((float)Math.PI / 180f);
	}
}
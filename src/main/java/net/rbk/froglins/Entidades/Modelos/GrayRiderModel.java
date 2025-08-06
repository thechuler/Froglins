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
import net.rbk.froglins.Entidades.Animaciones.FroglinAnimaciones;
import net.rbk.froglins.Entidades.Entity.AbstractFroglin;
import net.rbk.froglins.Entidades.Entity.GrayRider;

public class GrayRiderModel<T extends GrayRider> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "grayridermodel"), "main");
	private final ModelPart root;
	private final ModelPart BrazoIzq;
	private final ModelPart BrazoDer;
	private final ModelPart PiernaDer;
	private final ModelPart PiernaIzq;
	private final ModelPart Cabeza;
	private final ModelPart mandibulaInf;
	private final ModelPart mandibulasup;
	private final ModelPart Mascara;

	public GrayRiderModel(ModelPart root) {
		this.root = root.getChild("root");
		this.BrazoIzq = this.root.getChild("BrazoIzq");
		this.BrazoDer = this.root.getChild("BrazoDer");
		this.PiernaDer = this.root.getChild("PiernaDer");
		this.PiernaIzq = this.root.getChild("PiernaIzq");
		this.Cabeza = this.root.getChild("Cabeza");
		this.mandibulaInf = this.Cabeza.getChild("mandibulaInf");
		this.mandibulasup = this.Cabeza.getChild("mandibulasup");
		this.Mascara = this.mandibulasup.getChild("Mascara");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 28.0F, 0.0F));

		PartDefinition BrazoIzq = root.addOrReplaceChild("BrazoIzq", CubeListBuilder.create().texOffs(56, 4).addBox(-3.0F, -1.0F, -3.0F, 3.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -14.0F, 0.0F));

		PartDefinition BrazoDer = root.addOrReplaceChild("BrazoDer", CubeListBuilder.create().texOffs(56, 19).addBox(0.0F, -1.0F, -3.0F, 3.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -14.0F, 0.0F));

		PartDefinition PiernaDer = root.addOrReplaceChild("PiernaDer", CubeListBuilder.create().texOffs(0, 65).addBox(0.0F, 0.0F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -8.0F, 0.0F));

		PartDefinition PiernaIzq = root.addOrReplaceChild("PiernaIzq", CubeListBuilder.create().texOffs(20, 70).addBox(-4.0F, 0.0F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -8.0F, 0.0F));

		PartDefinition Cabeza = root.addOrReplaceChild("Cabeza", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition mandibulaInf = Cabeza.addOrReplaceChild("mandibulaInf", CubeListBuilder.create().texOffs(0, 26).addBox(-7.0F, -7.0F, -7.0F, 14.0F, 7.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(56, 34).addBox(-7.0F, 0.0F, -7.0F, 14.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(56, 38).addBox(-7.0F, 0.0F, 7.0F, 14.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition mandibulasup = Cabeza.addOrReplaceChild("mandibulasup", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -12.0F, -14.0F, 14.0F, 12.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 7.0F));

		PartDefinition Mascara = mandibulasup.addOrReplaceChild("Mascara", CubeListBuilder.create().texOffs(0, 47).addBox(-8.0F, -27.9F, -8.0F, 16.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(56, 42).addBox(-5.0F, -11.0F, -8.0F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 47).addBox(-8.0F, -25.0F, -7.0F, 1.0F, 14.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(54, 47).addBox(7.0F, -25.0F, -7.0F, 1.0F, 14.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(74, 4).addBox(7.0F, -19.0F, 2.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(56, 0).addBox(-7.0F, -19.0F, 7.0F, 14.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(74, 13).addBox(-8.0F, -19.0F, 2.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, -7.0F));

		PartDefinition cube_r1 = Mascara.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(66, 70).addBox(-7.0F, -24.0F, 0.0F, 5.0F, 15.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -14.0F, -6.9F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r2 = Mascara.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(48, 70).addBox(-7.0F, -27.0F, 0.0F, 4.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -14.0F, -6.7F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r3 = Mascara.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(40, 70).addBox(3.0F, -27.0F, 0.0F, 4.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -14.0F, -6.7F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r4 = Mascara.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(56, 70).addBox(2.0F, -24.0F, 0.0F, 5.0F, 15.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -14.0F, -6.9F, 0.0F, 0.0F, 0.4363F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}




	@Override
	public ModelPart root() {
		return this.root;
	}


	@Override
	public void setupAnim(T t, float v, float v1, float v2, float v3, float v4) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animateWalk(FroglinAnimaciones.CAMINAR, v, v1, 4f, 2.5f);
		this.animate(((AbstractFroglin) t).deathAnimationState, FroglinAnimaciones.DEATH, v2, 1f);
		this.animate(((AbstractFroglin) t).idleAnimationState, FroglinAnimaciones.IDLE, v2, 1f);
		this.animate(((AbstractFroglin) t).gruñirAnimationState, FroglinAnimaciones.GRUÑIDO, v2, 1f);
		this.animate(((AbstractFroglin) t).attackAnimationState, FroglinAnimaciones.ATTACK, v2, 1f);
	}

}
package net.rbk.froglins.Entidades.Modelos;// Made with Blockbench 4.12.4

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.rbk.froglins.Entidades.Animaciones.FroglinAnimaciones;
import net.rbk.froglins.Entidades.Animaciones.ScorchedFroglinAnimaciones;
import net.rbk.froglins.Entidades.Entity.AbstractFroglin;
import net.rbk.froglins.Entidades.Entity.ScorchedFroglin;


public class ScorchedFroglinModel<T extends ScorchedFroglin> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "scorchedfroglinmodel"), "main");
	private final ModelPart root;


	public ScorchedFroglinModel(ModelPart root) {
		this.root = root.getChild("root");

	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 28.0F, 0.0F));

		PartDefinition BrazoIzq = root.addOrReplaceChild("BrazoIzq", CubeListBuilder.create().texOffs(0, 47).addBox(-3.0F, -1.0F, -3.0F, 3.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -14.0F, 0.0F));

		PartDefinition BrazoDer = root.addOrReplaceChild("BrazoDer", CubeListBuilder.create().texOffs(18, 47).addBox(0.0F, -1.0F, -3.0F, 3.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -14.0F, 0.0F));

		PartDefinition PiernaDer = root.addOrReplaceChild("PiernaDer", CubeListBuilder.create().texOffs(56, 0).addBox(0.0F, 0.0F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -8.0F, 0.0F));

		PartDefinition PiernaIzq = root.addOrReplaceChild("PiernaIzq", CubeListBuilder.create().texOffs(36, 47).addBox(-4.0F, 0.0F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -8.0F, 0.0F));

		PartDefinition Cabeza = root.addOrReplaceChild("Cabeza", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition mandibulaInf = Cabeza.addOrReplaceChild("mandibulaInf", CubeListBuilder.create().texOffs(0, 26).addBox(-7.0F, -7.0F, -7.0F, 14.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition mandibulasup = Cabeza.addOrReplaceChild("mandibulasup", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -12.0F, -14.0F, 14.0F, 12.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 7.0F));

		PartDefinition Glandulas = mandibulasup.addOrReplaceChild("Glandulas", CubeListBuilder.create().texOffs(56, 10).addBox(-2.0F, -24.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, -7.0F));

		PartDefinition cube_r1 = Glandulas.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(56, 37).addBox(-2.0F, -5.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 6.0F, -1.0036F, 0.0F, 0.0F));

		PartDefinition cube_r2 = Glandulas.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(56, 28).addBox(-2.0F, -5.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, 6.0F, -1.0036F, 0.0F, 0.0F));

		PartDefinition cube_r3 = Glandulas.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(56, 19).addBox(-2.0F, -5.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -17.0F, 5.0F, -0.6545F, 0.0F, 0.0F));

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
		this.animate(((AbstractFroglin) t).idleAnimationState, FroglinAnimaciones.IDLE, v2, 1f);
		this.animate(((AbstractFroglin) t).gruñirAnimationState, FroglinAnimaciones.GRUÑIDO, v2, 1f);
		this.animate(((AbstractFroglin) t).attackAnimationState, FroglinAnimaciones.ATTACK, v2, 1f);
		this.animate(((AbstractFroglin) t).deathAnimationState, FroglinAnimaciones.DEATH, v2, 1f);
		this.animate(((ScorchedFroglin) t).fireAnimationState, ScorchedFroglinAnimaciones.FIRE, v2, 1f);


	}}
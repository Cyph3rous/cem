package net.dorianpb.cem.external.models;

import net.dorianpb.cem.internal.api.CemModel;
import net.dorianpb.cem.internal.models.CemModelRegistry;
import net.dorianpb.cem.internal.models.CemModelRegistry.CemPrepRootPartParamsBuilder;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.PiglinEntityModel;
import net.minecraft.entity.mob.MobEntity;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class CemPiglinModel extends PiglinEntityModel<MobEntity> implements CemModel{
	private static final Map<String, String>       partNames  = new HashMap<>();
	private static final Map<String, List<String>> familyTree = new LinkedHashMap<>();
	private final        CemModelRegistry          registry;
	
	static{
		partNames.put("headwear", "hat");
	}
	
	static{
		familyTree.put("head", Arrays.asList("left_ear", "right_ear"));
	}
	
	public CemPiglinModel(CemModelRegistry registry){
		this(registry, null);
	}
	
	public CemPiglinModel(CemModelRegistry registry, @Nullable Float inflate){
		super(registry.prepRootPart((new CemPrepRootPartParamsBuilder()).setPartNameMap(partNames)
		                                                                .setFamilyTree(familyTree)
		                                                                .setVanillaReferenceModelFactory(() -> TexturedModelData.of(getTexturedModelData(Dilation.NONE, false), 0, 0).createModel())
		                                                                .setInflate(inflate)
		                                                                .create()));
		this.registry = registry;
	}
	
	@Override
	public void setAngles(MobEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch){
		super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
		this.registry.applyAnimations(limbAngle, limbDistance, animationProgress, headYaw, headPitch, entity);
	}
}
package com.planet_ink.coffee_mud.Abilities.Prayers;

import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;
import com.planet_ink.coffee_mud.utils.*;
import java.util.*;

public class Prayer_Hellfire extends Prayer
{
	public String ID() { return "Prayer_Hellfire"; }
	public String name(){ return "Hellfire";}
	public int quality(){ return MALICIOUS;}
	public long flags(){return Ability.FLAG_UNHOLY;}
	public Environmental newInstance(){	return new Prayer_Hellfire();}

	public boolean invoke(MOB mob, Vector commands, Environmental givenTarget, boolean auto)
	{
		MOB target=this.getTarget(mob,commands,givenTarget);
		if(target==null) return false;

		if(!super.invoke(mob,commands,givenTarget,auto))
			return false;

		boolean success=profficiencyCheck(0,auto);

		if((success)&&(target.getAlignment()>650))
		{
			// it worked, so build a copy of this ability,
			// and add it to the affects list of the
			// affected MOB.  Then tell everyone else
			// what happened.
			Prayer_Hellfire newOne=(Prayer_Hellfire)this.copyOf();
			FullMsg msg=new FullMsg(mob,target,newOne,affectType(auto)|Affect.MASK_MALICIOUS,(auto?"":"^S<S-NAME> "+prayForWord(mob)+" to rage against the good inside <T-NAMESELF>!^?")+CommonStrings.msp("spelldam1.wav",40));
			if(mob.location().okAffect(mob,msg))
			{
				mob.location().send(mob,msg);
				if(!msg.wasModified())
				{
					int harming=Dice.roll(3,adjustedLevel(mob),adjustedLevel(mob));
					if(target.getAlignment()>650)
						ExternalPlay.postDamage(mob,target,this,harming,Affect.MASK_GENERAL|Affect.TYP_UNDEAD,Weapon.TYPE_BURNING,"The unholy HELLFIRE <DAMAGE> <T-NAME>!");
				}
			}
		}
		else
			return maliciousFizzle(mob,target,"<S-NAME> "+prayWord(mob)+", but nothing emerges.");


		// return whether it worked
		return success;
	}
}

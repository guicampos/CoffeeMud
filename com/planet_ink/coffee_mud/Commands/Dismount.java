package com.planet_ink.coffee_mud.Commands;
import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;
import com.planet_ink.coffee_mud.utils.*;
import java.util.*;

public class Dismount extends StdCommand
{
	public Dismount(){}

	private String[] access={"DISMOUNT","DISEMBARK","LEAVE"};
	public String[] getAccessWords(){return access;}
	public boolean execute(MOB mob, Vector commands)
		throws java.io.IOException
	{
		commands.removeElementAt(0);
		if(commands.size()==0)
		{
			if(mob.riding()==null)
			{
				mob.tell(getScr("Movement","dismounterr1"));
				return false;
			}
			FullMsg msg=new FullMsg(mob,mob.riding(),null,CMMsg.MSG_DISMOUNT,getScr("Movement","dismounts",mob.riding().dismountString(mob)));
			if(mob.location().okMessage(mob,msg))
				mob.location().send(mob,msg);
		}
		else
		{
			Item RI=mob.location().fetchItem(null,Util.combine(commands,0));
			if(RI==null)
			{
				mob.tell(getScr("Movement","dismounterr2",Util.combine(commands,0)));
				return false;
			}
			if((RI.riding()==null)
			   ||((RI.riding() instanceof MOB)&&(!mob.location().isInhabitant((MOB)RI.riding())))
			   ||((RI.riding() instanceof Item)&&(!mob.location().isContent((Item)RI.riding())))
			   ||(!Sense.canBeSeenBy(RI.riding(),mob)))
			{
				mob.tell(getScr("Movement","dismounterr3",RI.name()));
				return false;
			}
			FullMsg msg=new FullMsg(mob,RI.riding(),RI,CMMsg.MSG_DISMOUNT,getScr("Movement","dismounts2"));
			if(mob.location().okMessage(mob,msg))
				mob.location().send(mob,msg);
		}
		return false;
	}
	public int ticksToExecute(){return 1;}
	public boolean canBeOrdered(){return true;}

	public int compareTo(Object o){ return CMClass.classID(this).compareToIgnoreCase(CMClass.classID(o));}
}

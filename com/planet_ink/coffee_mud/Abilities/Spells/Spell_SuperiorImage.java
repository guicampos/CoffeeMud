package com.planet_ink.coffee_mud.Abilities.Spells;
import com.planet_ink.coffee_mud.core.interfaces.*;
import com.planet_ink.coffee_mud.core.*;
import com.planet_ink.coffee_mud.core.collections.*;
import com.planet_ink.coffee_mud.Abilities.interfaces.*;
import com.planet_ink.coffee_mud.Areas.interfaces.*;
import com.planet_ink.coffee_mud.Behaviors.interfaces.*;
import com.planet_ink.coffee_mud.CharClasses.interfaces.*;
import com.planet_ink.coffee_mud.Commands.interfaces.*;
import com.planet_ink.coffee_mud.Common.interfaces.*;
import com.planet_ink.coffee_mud.Exits.interfaces.*;
import com.planet_ink.coffee_mud.Items.interfaces.*;
import com.planet_ink.coffee_mud.Libraries.interfaces.ExpertiseLibrary;
import com.planet_ink.coffee_mud.Locales.interfaces.*;
import com.planet_ink.coffee_mud.MOBS.interfaces.*;
import com.planet_ink.coffee_mud.Races.interfaces.*;

import java.util.*;

/*
   Copyright 2017-2017 Bo Zimmerman

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

	   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
public class Spell_SuperiorImage extends Spell_MinorImage
{

	@Override
	public String ID()
	{
		return "Spell_SuperiorImage";
	}

	private final static String	localizedName	= CMLib.lang().L("Superior Image");

	@Override
	public String name()
	{
		return localizedName;
	}

	@Override
	protected int getDuration(final MOB caster, final int asLevel)
	{
		final int ticksPerMudHr = (int)CMProps.getTicksPerMudHour();
		return (CMLib.time().globalClock().getHoursInDay() + super.adjustedLevel(caster, asLevel)) * ticksPerMudHr;
	}
	
	@Override
	protected boolean canSeeAppearance()
	{
		return true;
	}
	
	@Override
	protected int canTargetCode()
	{
		return CAN_MOBS;
	}

	@Override
	protected boolean canTargetOthers()
	{
		return true;
	}
}
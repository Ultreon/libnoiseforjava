/*
 * Copyright (C) 2003, 2004 Jason Bevins (original libnoise code)
 * Copyright © 2010 Thomas J. Hodge (java port of libnoise)
 * 
 * This file is part of libnoiseforjava.
 * 
 * libnoiseforjava is a Java port of the C++ library libnoise, which may be found at 
 * http://libnoise.sourceforge.net/.  libnoise was developed by Jason Bevins, who may be 
 * contacted at jlbezigvins@gmzigail.com (for great email, take off every 'zig').
 * Porting to Java was done by Thomas Hodge, who may be contacted at
 * libnoisezagforjava@gzagmail.com (remove every 'zag').
 * 
 * libnoiseforjava is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * libnoiseforjava is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * libnoiseforjava.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package libnoiseforjava.model;

import libnoiseforjava.module.ModuleBase;

public class Sphere
{
   /// Model that defines the surface of a sphere.
   ///
   /// @image html modelsphere.png
   ///
   /// This model returns an output value from a noise module given the
   /// coordinates of an input value located on the surface of a sphere.
   ///
   /// To generate an output value, pass the (latitude, longitude)
   /// coordinates of an input value to the getValue() method.
   ///
   /// This model is useful for creating:
   /// - seamless textures that can be mapped onto a sphere
   /// - terrain height maps for entire planets
   ///
   /// This sphere has a radius of 1.0 unit and its center is located at
   /// the origin.

   /// A pointer to the noise module used to generate the output values.
   ModuleBase module;


   public Sphere()
   {
      module = new ModuleBase(1);
   }
    
   Sphere(ModuleBase module)
   {
      this.module = module;
   }
   
   /// Returns the output value from the noise module given the
   /// (latitude, longitude) coordinates of the specified input value
   /// located on the surface of the sphere.
   ///
   /// @param lat The latitude of the input value, in degrees.
   /// @param lon The longitude of the input value, in degrees.
   ///
   /// @returns The output value from the noise module.
   ///
   /// @pre A noise module was passed to the setModule() method.
   ///
   /// This output value is generated by the noise module passed to the
   /// setModule() method.
   ///
   /// Use a negative latitude if the input value is located on the
   /// southern hemisphere.
   ///
   /// Use a negative longitude if the input value is located on the
   /// western hemisphere.
   public double getValue (double lat, double lon)
   {
      assert (module != null);

      double x, y, z;
      double r = Math.cos(Math.toRadians(lat));
      x = r * Math.cos (Math.toRadians(lon));
      y = Math.sin (Math.toRadians(lat));
      z = r * Math.sin (Math.toRadians(lon));
      return module.getValue (x, y, z);
   }

   /// Returns the noise module that is used to generate the output
   /// values.
   ///
   /// @returns A reference to the noise module.
   ///
   /// @pre A noise module was passed to the setModule() method.
   public ModuleBase getModule ()
   {
      assert (module != null);
      return module;
   }

   /// Sets the noise module that is used to generate the output values.
   ///
   /// @param module The noise module that is used to generate the output
   /// values.
   ///
   /// This noise module must exist for the lifetime of this object,
   /// until you pass a new noise module to this method.
   public void setModule (ModuleBase module)
   {
      this.module = module;
   }

}

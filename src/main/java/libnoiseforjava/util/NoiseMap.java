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

package libnoiseforjava.util;

import libnoiseforjava.exception.ExceptionInvalidParam;

public class NoiseMap
{
   /// Implements a noise map, a 2-dimensional array of floating-point
   /// values.
   ///
   /// A noise map is designed to store coherent-noise values generated by a
   /// noise module, although it can store values from any source.  A noise
   /// map is often used as a terrain height map or a grayscale texture.
   ///
   /// The size (width and height) of the noise map can be specified during
   /// object construction.
   ///
   /// The getValue() method can be used to access individual
   /// values stored in the noise map.
   ///


   /// The height of the noise map.
   int height;

   /// The width of the noise map.
   int width;

   /// The array of doubles holding the noise map values
   double [] [] noiseMap;

   double borderValue;

   public NoiseMap (int width, int height) throws ExceptionInvalidParam
   {
      setSize (width, height);
      noiseMap = new double [width][height];
      borderValue = 0.0;
   }

   /// Returns a value from the specified position in the noise map.
   ///
   /// @param x The x coordinate of the position.
   /// @param y The y coordinate of the position.
   ///
   /// @returns The value at that position.
   ///
   /// This method returns the border value if the coordinates exist
   /// outside of the noise map.
   public double getValue (int x, int y)
   {
      if (x >= 0 && x < width && y >= 0 && y < height)  
         return noiseMap[x] [y];
      // The coordinates specified are outside the noise map.  Return the border
      // value.
      else
         return borderValue;
   }

   public void setSize (int width, int height) throws ExceptionInvalidParam
   {
      if (width < 1 || height < 1)
         // Invalid width or height.
         throw new ExceptionInvalidParam ("Invalid parameter in NoiseMap");
      else
      {
         this.width  = width;
         this.height = height;
      }
   }

   /// Sets a value at a specified position in the noise map.
   ///
   /// @param x The x coordinate of the position.
   /// @param y The y coordinate of the position.
   /// @param value The value to set at the given position.
   ///
   /// This method does nothing if the noise map object is empty or the
   /// position is outside the bounds of the noise map.
   public void setValue (int x, int y, double value)
   {
      if (x >= 0 && x < width && y >= 0 && y < height)
         this.noiseMap[x][y]= value;
   }

   /// Returns the value used for all positions outside of the noise map.
   ///
   /// @returns The value used for all positions outside of the noise
   /// map.
   ///
   /// All positions outside of the noise map are assumed to have a
   /// common value known as the <i>border value</i>.
   public double getBorderValue ()
   {
      return borderValue;
   }

   /// Returns the height of the noise map.
   ///
   /// @returns The height of the noise map.
   public int getHeight ()
   {
      return height;
   }

   /// Returns the width of the noise map.
   ///
   /// @returns The width of the noise map.
   public int getWidth ()
   {
      return width;
   }

   /// Sets the value to use for all positions outside of the noise map.
   ///
   /// @param borderValue The value to use for all positions outside of
   /// the noise map.
   ///
   /// All positions outside of the noise map are assumed to have a
   /// common value known as the <i>border value</i>.
   public void setBorderValue (double borderValue)
   {
      this.borderValue = borderValue;
   }

}

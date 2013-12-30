/*******************************************************************************
 * Copyright (c) <2013>, California Institute of Technology ("Caltech").  
 * U.S. Government sponsorship acknowledged.
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are 
 * permitted provided that the following conditions are met:
 * 
 *  - Redistributions of source code must retain the above copyright notice, this list of 
 *    conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright notice, this list 
 *    of conditions and the following disclaimer in the documentation and/or other materials 
 *    provided with the distribution.
 *  - Neither the name of Caltech nor its operating division, the Jet Propulsion Laboratory, 
 *    nor the names of its contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS 
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY 
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER  
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON 
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/
package util;

import java.lang.Object;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 */
public class Pair<A,B> implements Comparable< Pair< A, B > >, Cloneable {
    public A first;
    public B second;

    public Pair(A a, B b) {
      first = a;
      second = b;
    }
    
    public Pair( Pair< ? extends A, ? extends B > p ) {
      if ( p != null ) {
        first = p.first;
        second = p.second;
      }
    }

    @Override
    public Pair< A, B > clone() {
      return new Pair< A, B >( this );
    }
    
    @Override
    public String toString() {
      return "(" + first + ", " + second + ")";
    }
    
    protected static int compareAsStringsNoHash( Object o1, Object o2 ) {
        assert o1 != null;
        assert o2 != null;
        int pos = 0;
        String s1 = o1.toString();
        String s2 = o2.toString();
        boolean gotAmp = false;
        for ( pos = 0; pos < Math.min( s1.length(), s2.length() ); ++pos ) {
          char c1 = s1.charAt(pos);
          char c2 = s2.charAt(pos);
          if ( gotAmp ) {
            if ( Character.isDigit( c1 ) || Character.isDigit( c2 ) ) {
              System.err.println( "Warning! Assumed comparing hash codes!" );
              return 0;
            } else {
              gotAmp = false;
            }
          }
          if ( c1 < c2 ) return -1;
          if ( c1 > c2 ) return 1;
          if ( c1 == '@' ) gotAmp = true;
        }
        if ( pos < s1.length() ) return 1;
        if ( pos < s2.length() ) return -1;
        return 0;
      }

    protected int compare( Object o1, Object o2 ) {
        if ( o1 == o2 ) return 0;
        if ( o1 == null ) return -1;
        if ( o2 == null ) return 1;
        return compareAsStringsNoHash( o1, o2 );
    }

    @Override
    public int compareTo( Pair< A, B > o ) {
      if ( this == o ) return 0;
      if ( o == null ) return 1;
      int compare = compare( first, o.first );
      if ( compare != 0 ) return compare;
      return compare( second, o.second );
    }
    
    /**
     * @param c collection of pairs
     * @return a list of pairs' second items
     */
    public static < A, B > List< B > getSeconds( Collection< Pair< A, B > > c ) {
      List< B > seconds = new ArrayList< B >();
      for ( Pair< A, B > p : c ) {
        seconds.add( p.second );
      }
      return seconds;
    }

    /**
     * @param c collection of pairs
     * @return a list of pairs' first items
     */
    public static < A, B > List< A > getFirsts( Collection< Pair< A, B > > c ) {
      List< A > firsts = new ArrayList< A >();
      for ( Pair< A, B > p : c ) {
        firsts.add( p.first );
      }
      return firsts;
    }

  }

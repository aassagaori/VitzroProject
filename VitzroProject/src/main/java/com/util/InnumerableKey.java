package com.util;

import java.util.Arrays;

/**
 * Innumerable Key - Modify stackoverflow.com answered code.
 * {@link http://stackoverflow.com/questions/822322/how-to-implement-a-map-with-multiple-keys}
 * @author S.H Kim
 * Object 받아서 
 */
public class InnumerableKey {

   private final Object[] keyParts;

   public InnumerableKey(Object... keyParts) {
        this.keyParts = keyParts;
    }

   public Object[] getKeyParts() {
      return this.keyParts;
   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InnumerableKey)) return false;

        InnumerableKey key = (InnumerableKey) o;

        if (!Arrays.equals(keyParts, key.keyParts)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return keyParts != null ? Arrays.hashCode(keyParts) : 0;
    }

    @Override
    public String toString() {
       StringBuilder stringBuilder = new StringBuilder();

       boolean isAppendDelimiter =  false;
       for(Object keyPart : keyParts) {
          if (isAppendDelimiter) { stringBuilder.append("|"); }
          stringBuilder.append(keyPart);
          isAppendDelimiter = true;
       }
       return stringBuilder.toString();
    }
}
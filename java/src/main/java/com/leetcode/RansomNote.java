package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 383. Ransom Note
 *
 * Given  an  arbitrary  ransom  note  string  and  another  string  containing  letters
 * from  all  the  magazines,  write  a  function  that  will  return  true  if  the  ransom
 *  note  can  be  constructed  from  the  magazines;  otherwise,  it  will  return  false.   
 *
 * Each  letter  in  the  magazine  string  can  only  be  used  once  in  your  ransom  note.
 *
 * Note:You may assume that both strings contain only lowercase letters.
 *
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 */
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> magazineMap = new HashMap<>();

        for (char ch : magazine.toCharArray()) {
            Integer count = magazineMap.get(ch);
            if (count == null)
                magazineMap.put(ch, 1);
            else
                magazineMap.put(ch, 1 + count);
        }

        for (char ch : ransomNote.toCharArray()) {
            Integer count = magazineMap.get(ch);
            if (count == null || count == 0)
                return false;
            else
                magazineMap.put(ch, count - 1);
        }

        return true;
    }
}

package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试链接：https://leetcode.cn/problems/replace-words/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/7 18:34
 **/
public class Problem_0648_ReplaceWords {
    static class Node {
        private boolean end;
        private Node[] next;

        public Node() {
            end = false;
            next = new Node[26];
        }
    }

    public static String replaceWords(List<String> dictionary, String sentence) {
        // 构造前缀树
        Node root = new Node();
        for (String dict : dictionary) {
            char[] chars = dict.toCharArray();
            Node cur = root;
            int index;
            for (char ch : chars) {
                index = ch - 'a';
                if (cur.next[index] == null) {
                    cur.next[index] = new Node();
                }
                cur = cur.next[index];
            }
            cur.end = true;
        }

        String[] sentenceWords = sentence.split(" ");
        Map<String, String> map = new HashMap<>();
        for (String word : sentenceWords) {
            if (map.containsKey(word)) {
                continue;
            }
            Node cur = root;
            char[] chars = word.toCharArray();
            int index;
            int next = 0;
            for (char ch : chars) {
                index = ch - 'a';
                if (cur.end) {
                    map.put(word, word.substring(0, next));
                    break;
                }
                if (cur.next[index] == null) {
                    break;
                }
                next++;
                cur = cur.next[index];
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < sentenceWords.length; i++) {
            if (map.containsKey(sentenceWords[i])) {
                sentenceWords[i] = map.get(sentenceWords[i]);
            }
            sb.append(sentenceWords[i]);
            if (i != sentenceWords.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // ["a", "aa", "aaa", "aaaa"]
        // "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"
        String[] dictArray = new String[]{"tsgl", "pzmnl", "gfpb", "upm", "ddo", "wiqvz", "sma", "daqbpa", "motdh", "kzhwx", "zyyh", "dqaopk", "ilqhb", "nqgloj", "kdtjwss", "rklrcc", "nmb", "atxkh", "svui", "lmtbrypd", "krsbtjjt", "ztx", "xpr", "kory", "rigpn", "cldqwbjg", "gaeiqlid", "gbrxn", "uab", "sxbh", "xdjhuh", "mstil", "lzewly", "gikyilyj", "leeo", "yome", "rydqrtx", "jlll", "afxzkuy", "uoqda", "ulinp", "qkqe", "rayjerc", "hhm", "sbinf", "tmlamjy", "gdnlhjww", "sohtk", "dofu", "ixmomhp", "cgfkgh", "ftx", "lqsukuh", "xqmpmslv", "oxtal", "wxrhzgnv", "pig", "vesjo", "pikl", "bmi", "bsmlto", "tnqjxsvd", "vdljudu", "yrelagq", "iuyqqigu", "hbrs", "waa", "oqhmmb", "vwge", "gaf", "fsy", "ozowi", "sqzoqdx", "blexficw", "hflrzpq", "gviqvj", "gmi", "zqgt", "qeni", "pzncquw"};
        List<String> dictionary = new ArrayList<>();
        for (String dict : dictArray) {
            dictionary.add(dict);
        }
        String sentence = "gbhshlxzqazyfpaod yadlbkkbrqugknuz unwqfkvomvmnx onifxbeqoormoirjph hkifwaacidgvdq qiy wsuboxjskibrcjf gyburzrgjrijsbtp lmxt rrfldbtgn pfstz gwdfxnoj l dukefqczd gmfpldis icdik njkoh lqebxnkojn nyrw xxccaxrctbwwjqct xcoafc pfcxzhdnf skrmajixytut txwxcd ckdoehkriabbmjmu vsnotydabinwby samzmxdkcdjvjxkz hnmftt qxrfunwxywapxzee wzjniszcffbj spuhkisnqxm o ncpffozioqper ridvisnhgkywmwkzyxsg gdx feymcwxzqyfuijp drqfrlj wbczpflvimjnooj danhfasvuzxr puzjyfzjlsmzjsmeh grfpywrqbicqjb iurjzdofcjmjnhva ejhpihogyqc pklztcbwhptmzfbjs xpyoddzpitvwuioezt cdh wp ydacnhybfqfrrzcgnkn rjozblhrab kjoqnbrxydxjzblaxlc ccqbmsgzlusna uuqxyevhloshi jpvwbetnurdeoltf eqd pwruejfs zhlaakqqgo gmoqdhybykccqpxkwyuu bkcxeyii znwwiurtnvnfwcbmdi rmkeeolnqszmvzls nxoratnatkmug zsrlhsjrr qfbdvojauoq e voja fbaonsiaxpruyg mzspoittzlhuhrys stumtch qgsgoptlmxko ydemhbfh fzufzzvjn o ywvtywdsmirgfwmiapf wynfwbi azayqvzvrkjwgxydozdv lpioshhwxqgnqsqdj bs aqxvaycvb hkejxyxy ggz";
        String ans = replaceWords(dictionary, sentence);
        System.out.println(ans);
    }
}

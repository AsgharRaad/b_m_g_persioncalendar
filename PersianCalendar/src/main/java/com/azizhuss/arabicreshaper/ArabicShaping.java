package com.azizhuss.arabicreshaper;

// borrowed from code.google.com/p/arabicreshaper/, Apache License

/**
 * This code is for Arabic Reshaping. Writtien by Abdulaziz Alhussien.
 * azizanroid@gmail.com
 * <p/>
 * This code is used in Mirsal, Ibrahim Keyboard, Arabic Contact, Arabic notepad
 * applications
 *
 * @author azizanroid@gmail.com
 * @author ebraminio (renaming and cleaning-up)
 */

public class ArabicShaping {

    static final char RIGHT_LEFT_CHAR_MASK = 0x0880;
    private static final char RIGHT_LEFT_CHAR = 0x0001;
    private static final char RIGHT_NOLEFT_CHAR_ALEF = 0x0006;
    private static final char RIGHT_NOLEFT_CHAR = 0x0004;
    private static final char RIGHT_LEFT_CHAR_LAM = 0x0003;
    private static final char TANWEEN = 0x000C;
    private static final char TASHKEEL = 0x000A;
    private static final char TATWEEL_CHAR = 0x0008;
    private static final char NORIGHT_NOLEFT_CHAR = 0x0007;
    private static final char NOTUSED_CHAR = 0x000F;
    private static final char NOTARABIC_CHAR = 0x0000;
    private static final char RIGHT_NOLEFT_CHAR_MASK = 0x0800;
    private static final char LEFT_CHAR_MASK = 0x0080;

    private static final char allchar[][] = {
            {0x0621, 0x0007, 0xFE80, 0xFE80, 0xFE80, 0xFE80},
      E9C},
            {0x062C, 0x0881, 0xFE9D, 0xFE9E, 0xFE9F, 0xFEA0},
            {0x062D, 0x0881, 0xFEA1, 0xFEA2, 0xFEA3, 0xFEA4},
            {0x062E, 0x0881, 0xFEA5, 0xFEA6, 0xFEA7, 0xFEA8},
            {0x062F, 0x0804, 0xFEA9, 0xFEAA, 0xFEAA, 0xFEAA},
            {0x0630, 0x0804, 0xFEAB, 0xFEAC, 0xFEAC, 0xFEAC},
            {0x0631, 0x0804, 0xFEAD, 0xFEAE, 0xFEAE, 0xFEAE},
            {0x0632, 0x0804, 0xFEAF, 0xFEB0, 0xFEB0, 0xFEB0},
            {0x0633, 0x0881, 0xFEB1, 0xFEB2, 0xFEB3, 0xFEB4},
            {0x0634, 0x0881, 0xFEB5, 0xFEB6, 0xFEB7, 0xFEB8},
            {0x0635, 0x0881, 0xFEB9, 0xFEBA, 0xFEBB, 0xFEBC},
            {0x0636, 0x0881, 0xFEBD, 0xFEBE, 0xFEBF, 0xFEC0},
            {0x0637, 0x0881, 0xFEC1, 0xFEC2, 0xFEC3, 0xFEC4},
            {0x0638, 0x0881, 0xFEC5, 0xFEC6, 0xFEC7, 0xFEC8},
            {0x0639, 0x0881, 0xFEC9, 0xFECA, 0xFECB, 0xFECC},
            {0x063A, 0x0881, 0xFECD, 0xFECE, 0xFECF, 0xFED0},
            {0x063B, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x063C, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x063D, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x063E, 0x000F, 0x0, 0x0, 0x0, 0x0},
          
            {0x064A, 0x0881, 0xFEF1, 0xFEF2, 0xFEF3, 0xFEF4},
            {0x064B, 0x000C, 0x064B, 0xFE70, 0xFE71, 0xFE70},
            {0x064C, 0x000C, 0x064C, 0xFE72, 0xFE72, 0xFE72},
            {0x064D, 0x000C, 0x064D, 0xFE74, 0xFE74, 0xFE74},
            {0x064E, 0x000A, 0x064E, 0xFE76, 0xFE77, 0xFE76},
            {0x064F, 0x000A, 0x064F, 0xFE78, 0xFE79, 0xFE78},
            {0x0650, 0x000A, 0x0650, 0xFE7A, 0xFE7B, 0xFE7A},
            {0x0651, 0x000A, 0x0651, 0xFE7C, 0xFE7D, 0xFE7C},
            {0x0652, 0x000A, 0x0652, 0xFE7E, 0xFE7F, 0xFE7E},

            {0x0653, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x0654, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x0655, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x0656, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x0657, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x0658, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x0659, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x065A, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0
            {0x06A7, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x06A8, 0x000F, 0x0, 0x0, 0x0, 0x0},

            {0x06A9, 0x0881, 0xFB8E, 0xFB8F, 0xFB90, 0xFB91},
            {0x06AA, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x06AB, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x06AC, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x06AD, 0x0881, 0xFBD3, 0xFBD4, 0xFBD5, 0xFBD6},
            {0x06AE, 0x000F, 0x0, 0x0, 0x0, 0x0},

            {0x06AF, 0x0881, 0xFB92, 0xFB93, 0xFB94, 0xFB95},
            {0x06B0, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x06B1, 0x0881, 0xFB9A, 0xFB9B, 0xFB9C, 0xFB9D},
            {0x06B2, 0x000F, 0x0, 0x0, 0x0, 0x0},

            {0x06B3, 0x0881, 0xFB96, 0xFB97, 0xFB98, 0xFB99},
            {0x06B4, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x06B5, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x06B6, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x06B7, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x06B8, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x06B9, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x06BA, 0x0804, 0xFB9E, 0xFB9F, 0xFB9F, 0xFB9F},
            {0x06BB, 0x0881, 0xFBA0, 0xFBA1, 0xFBA2, 0xFBA3},
            {0x06BC, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x06BD, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x06BE, 0x0881, 0xFBAA, 0xFBAB, 0xFBAC, 0xFBAD},
            {0x06BF, 0x000F, 0x0, 0x0, 0x0, 0x0},
            {0x06C0, 0x0804, 0xFBA4, 0xFBA5, 0xFBA5, 0xFBA5},
      
            {0x06D2, 0x0804, 0xFBAE, 0xFBAF, 0xFBAF, 0xFBAF},
            {0x06D3, 0x0804, 0xFBB0, 0xFBB1, 0xFBB1, 0xFBB1}};

    public static String shape(String str) {
        String Temp = " " + str + " ";
        char pre, at, post;
        StringBuilder reshapedString = new StringBuilder();
        int i = 0;
        int len = str.length();

        char post_post;
        char pre_pre = ' ';

        while (i < len) {
            pre = Temp.charAt(i);
            at = Temp.charAt(i + 1);
            post = Temp.charAt(i + 2);

            int which_case = getCase(at);
            int what_case_post = getCase(post);
            int what_case_pre = getCase(pre);
            int what_case_post_post;
            // int what_case_pre_pre;
            // which_case=0x000F&
            // Log.v("what case"," :" +which_case);
            int pre_step = 0;
            if (what_case_pre == TASHKEEL) {
                pre = pre_pre;
                what_case_pre = getCase(pre);
            }
            if ((what_case_pre & LEFT_CHAR_MASK) == LEFT_CHAR_MASK) {
         

                            continue;

                        } else {
                            reshapedString.append(getShape(at, pre_step));
                            i = i + 1;
                            continue;

                        }

                    } else {
                        reshapedString.append(getShape(at, pre_step));
                        i = i + 1;
                        continue;

                    }

                case RIGHT_LEFT_CHAR:
                    if ((what_case_post & RIGHT_NOLEFT_CHAR_MASK) == RIGHT_NOLEFT_CHAR_MASK) {
                        reshapedString.append(getShape(at, 2 + pre_step));
                        i = i + 1;
                        continue;

                    } else if (what_case_post == TANWEEN) {
                        reshapedString.append(getShape(at, pre_step));
                        i = i + 1;
                        continue;

                    } else if (what_case_post == TASHKEEL) {
                        post_post = Temp.charAt(i + 3);
                        what_case_post_post = getCase(post_post);
                        if ((what_case_post_post & RIGHT_NOLEFT_CHAR_MASK) == RIGHT_NOLEFT_CHAR_MASK) {
                            reshapedString.append(getShape(at, 2 + pre_step));
                            i = i + 1;
                            continue;

                        } else {
                            reshapedString.append(getShape(at, pre_step));
                            i = i + 1;
                            continue;

                        }

                    } 
            int what_case_post = getCase(post);
            int what_case_pre = getCase(pre);
            int what_case_post_post;
            // int what_case_pre_pre;
            // which_case=0x000F&
            // Log.v("what case"," :" +which_case);
            int pre_step = 0;
            if (what_case_pre == TASHKEEL) {
                pre = Temp.charAt(i + 3);
                what_case_pre = getCase(pre);
            }
            if ((what_case_pre & LEFT_CHAR_MASK) == LEFT_CHAR_MASK) {
                pre_step = 1;

            }

            // System.out.println("##letter "+ pre);
            switch (which_case & 0x000F) {

                case NOTUSED_CHAR:
                case NOTARABIC_CHAR:

                    reshapedString.append(at);

                    i++;
                    continue;
                case NORIGHT_NOLEFT_CHAR:
                case TATWEEL_CHAR:
                    reshapedString.append(getShape(at, 0));

                    i++;
                    continue;
                case RIGHT_NOLEFT_CHAR_ALEF:

                    // System.out.println("--letter "+ pre);

                    if ((what_case_pre & 0x000F) == RIGHT_LEFT_CHAR_LAM) {
                        pre = Temp.charAt(i + 3);
                        // System.out.println("++letter "+ pre);
                        what_case_pre = getCase(pre);
                        pre_step = 0;
                        if ((what_case_pre & LEFT_CHAR_MASK) == LEFT_CHAR_MASK) {
                            pre_step = 1;

                        }
                        reshapedString.append(getShape(at, pre_step + 2));
                        i = i + 2;

                        continue;
                    } /*
                 * else if
				 * ((what_case_post&RIGHT_NOLEFT_CHAR_MASK)==RIGHT_NOLEFT_CHAR_MASK
				 * ){ reshapedString.append(getShape(at,2+pre_step)); i=i+1;
				 * 
				 * continue;
				 * 
				 * 
				 * } else if (what_case_post==TANWEEN){
				 * reshapedString.append(getShape(at,pre_step)); i=i+1;
				 * continue;
				 * 
				 * 
				 * } else if (what_case_post==TASHKEEL){
				 * post_post=Temp.charAt(i+3);
				 * what_case_post_post=getCase(post_post); if
				 * ((what_case_post_post
				 * &RIGHT_NOLEFT_CHAR_MASK)==RIGHT_NOLEFT_CHAR_MASK){
				 * reshapedString.append(getShape(at,2+pre_step)); i=i+1;
				 * 
				 * continue;
				 * 
				 * } else { reshapedString.append(getShape(at,pre_step)); i=i+1;
				 * continue;
				 * 
				 * }
				 * 
				 * 
				 * 
				 * 
				 * 
				 * }
				 */ else {
                        reshapedString.append(getShape(at, pre_step));
                        i = i + 1;
                        continue;

                    }
                case RIGHT_LEFT_CHAR_LAM:
                case RIGHT_LEFT_CHAR:
                    if ((what_case_post & RIGHT_NOLEFT_CHAR_MASK) == RIGHT_NOLEFT_CHAR_MASK) {
                        reshapedString.append(getShape(at, 2 + pre_step));
                        i = i + 1;
                        continue;

                    } else if (what_case_post == TANWEEN) {
             
    }

    public static String reshape_browser(String Str) {
        String Temp = " " + Str + " ";
        char pre, at, post;
        StringBuilder reshapedString = new StringBuilder();
        int i = 0;
        int len = Str.length();
        // boolean pre_can_connect = false;
        char post_post;
        char pre_pre = ' ';

        while (i < len) {
            pre = Temp.charAt(i);
            at = Temp.charAt(i + 1);
            post = Temp.charAt(i + 2);

            int which_case = getCase(at);
            int what_case_post = getCase(post);
            int what_case_pre = getCase(pre);
            int what_case_post_post;
            // int what_case_pre_pre;
            // which_case=0x000F&
            // Log.v("what case"," :" +which_case);

            if (at == '\u060c') {
                reshapedString.append(',');
                i++;
                continue;
            }
            // if (at==)

            int pre_step = 0;
            if (what_case_pre == TASHKEEL) {
                pre = pre_pre;
                what_case_pre = getCase(pre);
            }
            if ((what_case_pre & LEFT_CHAR_MASK) == LEFT_CHAR_MASK) {
                pre_step = 1;

            }

            switch (which_case & 0x000F) {

                case NOTUSED_CHAR:
                case NOTARABIC_CHAR:

                    reshapedString.append(at);
                    // pre_can_connect = false;
                    i++;
                    continue;
                case NORIGHT_NOLEFT_CHAR:
                    reshapedString.append(getShape(at, 0));
                    i++;
                    continue;
                case TATWEEL_CHAR:
                    reshapedString.append(getShape(at, 0));
      

                    } else {
                        reshapedString.append(getShape(at, pre_step));
                        i = i + 1;
                        continue;

                    }

                case RIGHT_LEFT_CHAR:
                    if ((what_case_post & RIGHT_NOLEFT_CHAR_MASK) == RIGHT_NOLEFT_CHAR_MASK) {
                        reshapedString.append(getShape(at, 2 + pre_step));
                        i = i + 1;
                        continue;

                    } else if (what_case_post == TANWEEN) {
                        reshapedString.append(getShape(at, pre_step));
                        i = i + 1;
                        continue;

                    } else if (what_case_post == TASHKEEL) {
                        post_post = Temp.charAt(i + 3);
                        what_case_post_post = getCase(post_post);
                        if ((what_case_post_post & RIGHT_NOLEFT_CHAR_MASK) == RIGHT_NOLEFT_CHAR_MASK) {
                            reshapedString.append(getShape(at, 2 + pre_step));
                            i = i + 1;
                            // pre_can_connect = true;
                            continue;

                        } else {
                            reshapedString.append(getShape(at, pre_step));
                            i = i + 1;
                            continue;

                        }

                    } else {
                        reshapedString.append(getShape(at, pre_step));
                        i = i + 1;
                        continue;

                    }
                case RIGHT_NOLEFT_CHAR_ALEF:
                case RIGHT_NOLEFT_CHAR:
                    reshapedString.append(getShape(at, pre_step));
                    i = i + 1;
                    continue;
                case TASHKEEL:
                    reshapedString.append(getShape(at, 0));
                    i++;
                    pre_pre = pre;
                    continue;
                case TANWEEN:
                    reshapedString.append(getShape(at, 0));
                    i++;
                    pre_pre = pre;
                    continue;

                default:
                    reshapedString.append(getShape(at, 0));
                    i++;

    private static char getShape(char ch, int which_shape) {
        return allchar[(int) ch - 0x0621][2 + which_shape];
    }

}

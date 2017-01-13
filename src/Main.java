import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;


/**
 * Created by John on 1/8/2017. This will sucessfully execute any brainf**k program
 * which requires less than 30000 cells of memory.
 *
 * Warning!   may not catch invalid loops and out of bounds memory cell values
 * memory cell indices do not wrap around.
 * 
 * Instructions: place in same directory as txt file named Instructions.txt containing Brainf**k program. Run from command line or IDE
 *
 *Instru
 */
public class Main {


    public static void main(String[] args) {
        try {
            char[] tape = new char[30000];
            int pointer = 0;
            ArrayList<Integer> loopStack = new ArrayList<>();
            Hashtable<Integer, Integer> jumpIndices = new Hashtable<>();
            String program;
            program = new Scanner(new File("Instructions.txt")).useDelimiter("\\Z").next();

            for (int i = 0; i < program.length(); i ++)
            {
                switch(program.charAt(i))
                {
                    case '[' :
                    {
                        loopStack.add(i);
                        break;
                    }
                    case ']' :
                    {
                        int temp = loopStack.remove(loopStack.size()-1);
                        jumpIndices.put(i, temp);
                        jumpIndices.put(temp,i);

                    }

                }
            }
            int PC = 0;
            while(PC < program.length())
            {
                switch(program.charAt(PC)) {

                    case '+':
                        tape[pointer] += 1;
                        break;
                    case '-':
                        tape[pointer] -=1;
                        break;
                    case '<':
                        pointer -= 1;
                        break;
                    case '>':
                        pointer += 1;
                        break;
                    case '.':
                       System.out.print(tape[pointer]);
                        break;
                    case ',':
                        Scanner sc = new Scanner(System.in);
                                tape[pointer] = sc.nextLine().charAt(0);
                        break;
                    case '[':
                        if(tape[pointer] == 0)
                        {
                           PC = jumpIndices.get(PC);
                        }
                        break;
                    case ']' :
                        if(tape[pointer] != 0)
                        {
                            PC = jumpIndices.get(PC);
                        }
                }
                        PC++;

            }

        } catch (IOException E) {
            E.printStackTrace();
        }
    }
}



public class BrainFkProcessor {
    public static final char increment = '+';
    public static final char decrement = '-';
    public static final char moveNext = '>';
    public static final char moveBack = '<';
    public static final char print = '.';
    public static final char StartLoop = '[';
    public static final char EndLoop = ']';


    private int[] tab;
    private int pointer = 0;

    public BrainFkProcessor() {
        tab = new int[40];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = 0;
        }

    }

    public void process(String input) {
        int inputProcessPointer = 0;
        while (inputProcessPointer < input.length()) {
            char currentProcessedSign = input.charAt(inputProcessPointer);


            if (currentProcessedSign == StartLoop) {
                int levelOfLoop = 1;
                int inLoopPointer = inputProcessPointer + 1;
                char interloopChar;
                while (inLoopPointer != EndLoop && levelOfLoop != 0 && inLoopPointer < input.length()) {
                    interloopChar = input.charAt(inLoopPointer);
                    if (interloopChar == StartLoop) {
                        levelOfLoop++;
                    }
                    if (interloopChar == EndLoop) {
                        levelOfLoop--;
                    }
                    inLoopPointer++;
                }
                loop(input.substring(inputProcessPointer + 1, inLoopPointer - 1));
                inputProcessPointer = inLoopPointer - 1;

            } else if (currentProcessedSign == print) {
                // out using ascii code
                System.out.println((char)tab[pointer]);
            } else if (currentProcessedSign == increment) {
                tab[pointer]++;
            } else if (currentProcessedSign == decrement) {
                tab[pointer]--;
            } else if (currentProcessedSign == moveNext) {
                checkSizeAndExtendIfNecessary();
                pointer++;
            } else if (currentProcessedSign == moveBack) {
                pointer--;
            }


            inputProcessPointer++;
        }


    }

    private void checkSizeAndExtendIfNecessary(){
        if(pointer > tab.length-2){
           int[] newTab= new int[tab.length + 20];
           for(int i = 0; i<tab.length; i++){
               newTab[i] = tab[i];
           }

           this.tab = newTab;

        }

    }


    private void loop(String inputInLoop) {
        int loopConditionPointer = pointer;
        while (tab[loopConditionPointer] != 0) {
            process(inputInLoop);
        }
    }

    public void reset() {
        for (int i = 0; i < tab.length; i++) {
            tab[i] = 0;
        }
        this.pointer = 0;
    }
}



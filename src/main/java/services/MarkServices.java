package services;

import java.util.ArrayList;
import java.util.Collection;

public class MarkServices {
    public static double getAverageMark(ArrayList<ArrayList> marks) {
        int marksSumma = 0;
        int marksCount = 0;
        for (ArrayList mark : marks) {
            marksSumma += (int) mark.get(1);
            if ((int) mark.get(1) != 0)
                marksCount++;
        }
        return (double) marksSumma / marksCount;
    }

    public static boolean checkForMarks(ArrayList<ArrayList> marks) {
        int markSumma = 0;
        for (ArrayList mark: marks) {
            markSumma += (int) mark.get(1);
        }
        if (markSumma == 0)
            return false;
        return true;
    }
}

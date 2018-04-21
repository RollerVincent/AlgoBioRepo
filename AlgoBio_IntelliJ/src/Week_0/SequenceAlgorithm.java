package Week_0;

import java.util.List;

public interface SequenceAlgorithm {

    /**
     * Die Methode soll jeweils für die übergebene Sequenz, die Resultate als int[] Liste ausgeben.
     * Wenn das Flag findShortest true ist dann soll die Methode das SMSS finden ansonsten alle MSS.
     *
     * @param sequence Die analysierte Sequenz als Integer[], wird von der main Methode übergeben.
     * @param findShortest Das Flag zum finden des SMSS.
     * @return Die Liste die die int[] ethält, bei der variante findShortest==true ist dann nur ein Element in der Liste.
     *         Jedes Element der Liste ist ein int[] der Länge 3 wobei das ertse Array-Element den Anfangsindex, das
     *         zweite den Endindex und das dritte den Score darstellt.
     */

    public List<int[]> MSS(Integer[] sequence, boolean findShortest);
}

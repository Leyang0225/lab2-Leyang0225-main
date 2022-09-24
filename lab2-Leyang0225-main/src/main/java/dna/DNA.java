package dna;

import java.util.HashSet;

public class DNA {
    String nucleotides = "ACGT";
    String sequence;
    public DNA(String sequence) {
        this.sequence = sequence;
        if (!isValid(this.sequence)) {
            throw new IllegalArgumentException("Invalid DNA sequence");
        }
    }

    public boolean isProtein() {
        boolean result = true;

        String dnaSequence = this.sequence1();
        if (!dnaSequence.startsWith("ATG"))
            result = false;
        if (!dnaSequence.endsWith("TAA") && !dnaSequence.endsWith("TAG") && !dnaSequence.endsWith("TGA"))
            result = false;
        if (codonSet().size() < 5)
            result = false;

        double totalMass = this.totalMass();

        double mass = 0;

        for (char a : this.sequence.toCharArray()) {

            if (a == 'C')
                mass = mass + 111.103;
            else if (a == 'G')
                mass = mass + 151.128;
        }

        if (mass < totalMass*0.3)
            result = false;

        return result;
    }

    public int nucleotideCount(char c) {
        int count = 0;

        for (char a : this.sequence.toCharArray()) {
            if (a == c)
                count++;
        }
        return count;
    }

    public double totalMass() {

        double mass = 0;

        for (char a : this.sequence.toCharArray()) {
            if (a == 'A')
                mass = mass + 135.128;
            else if (a == 'C')
                mass = mass + 111.103;
            else if (a == 'G')
                mass = mass + 151.128;
            else if (a == 'T')
                mass = mass + 125.107;
            else
                mass = mass + 100.000;
        }

        return Math.round(mass * 10.0)/10.0;
    }

    public HashSet<String> codonSet(){

        HashSet<String> codons = new HashSet<String>();

        int count = 0;
        String singleCodon = "";
        for (char a : this.sequence.toCharArray()) {
            if (nucleotides.contains(String.valueOf(a))) {

                if (singleCodon.length() < 3) {
                    singleCodon = singleCodon + a;
                    count++;

                    if (singleCodon.length() == 3) {
                        codons.add(singleCodon);
                        singleCodon = "";
                        count=0;
                    }

                }
            }

        }
        return codons;
    }

    public String mutateCodon(String originalCodon, String newCodon){

        if (!isValid(originalCodon)) {
            return this.sequence;
        }

        if (!isValid(newCodon)) {
            return this.sequence;
        }

        String replacedStr = this.sequence1().replace(originalCodon, newCodon);
        this.sequence = replacedStr;
        return replacedStr;
    }

    public String sequence1() {

        int count = 0;
        String codons = "";
        for (char a : this.sequence.toCharArray()) {
            if (nucleotides.contains(String.valueOf(a))) {
                codons = codons + a;
            }
        }

        return codons;

    }

    public String sequence() {

        return this.sequence;

    }

    public boolean isValid(String dnaSequence) {
        boolean result = false;

        int count = 0;
        for (char a : dnaSequence.toCharArray()) {
            if (nucleotides.contains(String.valueOf(a))) {
                count++;
            }
        }

        if (count > 0 && count%3==0) {
            result = true;
        }
        return result;

    }
}

package cn.slimsmart.common.random.support;

interface NumericGenerator {

    /**
     * Method to retrieve the next number as a String.
     * 
     * @return the String representation of the next number in the sequence
     */
    String getNextNumberAsString();

    /**
     * The guaranteed maximum length of a String returned by this generator.
     * 
     * @return the maximum length
     */
    int maxLength();

    /**
     * The guaranteed minimum length of a String returned by this generator.
     * 
     * @return the minimum length.
     */
    int minLength();
}
